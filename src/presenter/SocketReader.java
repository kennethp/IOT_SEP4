package presenter;

import java.io.*;
import java.net.Socket;
import model.*;

public class SocketReader implements Runnable {
	Socket socket;
	IWebserviceHandler webserviceHandler;
	Thread t;
	boolean auth = false;
	String authToken = "1010";

	public SocketReader(Socket socket, IWebserviceHandler webserviceHandler) {
		this.socket = socket;
		this.webserviceHandler = webserviceHandler;
	}

	public void kill() {
		System.out.println("Connection terminated");
		try {
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		t.interrupt();
	}

	@Override
	public void run() {
		t = Thread.currentThread();
		System.out.println("Connected");
		InputStreamReader ir;
		BufferedWriter bw;
		try {
			ir = new InputStreamReader(socket.getInputStream());
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while(!socket.isClosed()) {
				int c;
				while((c = ir.read()) != 0) {
					baos.write(c);
				}

				String str = new String(baos.toByteArray(), "UTF-8");
				baos.reset();
				handle(str, bw);
			}
		} catch(IOException e) {
			System.out.println("SocketReader Interrupted");
		}
	}

	private void handle(String input, BufferedWriter bw) throws IOException {
		if(!auth) {
			if(input.equals(authToken)) {
				auth = true;
				respond("accept", bw);
			}
			else {
				respond("reject", bw);
			}
			return;
		}

		int c = input.indexOf(':');
		String cmd = "";
		String param = "";
		if(c != -1) {
			cmd = input.substring(0, c);
			param = input.substring(c + 1, input.length());
		}

		String res;
		switch(cmd) {
			case "getplantprofile":
				int id;
				try {
					id = Integer.parseInt(param);
				} catch(NumberFormatException e) {
					id = -1;
				}
				PlantProfile p = webserviceHandler.getPlantProfile(id);

				if(p == null) {
					res = "null";
				}
				else {
					res = p.toJson();
				}

				respond(res, bw);
				break;

			case "getaccount":
				Account a = webserviceHandler.getAccount(param);

				if(a == null) {
					res = "null";
				}
				else {
					res = a.toJson();
				}

				respond(res, bw);
				break;

			case "removeaccount":
				if(webserviceHandler.removeAccount(param)) {
					res = "accept";
				}
				else {
					res = "reject";
				}

				respond(res, bw);
				break;

			case "removeplantprofile":
				int idP;
				try {
					idP = Integer.parseInt(param);
				} catch(NumberFormatException e) {
					idP = -1;
				}

				if(webserviceHandler.removePlantProfile(idP)) {
					res = "accept";
				}
				else {
					res = "reject";
				}

				respond(res, bw);
				break;

			case "addaccount":
				Account addAccount = Account.fromJson(param);
				if(addAccount == null) {
					res = "reject";
					break;
				}

				if(webserviceHandler.addAccount(addAccount)) {
					res = "accept";
				}
				else {
					res = "reject";
				}

				respond(res, bw);
				break;

			case "modifyaccount":
				Account modAccount = Account.fromJson(param);
				if(modAccount == null) {
					res = "reject";
					break;
				}

				if(webserviceHandler.modifyAccount(modAccount)) {
					res = "accept";
				}
				else {
					res = "reject";
				}

				respond(res, bw);
				break;

			default:
				respond("Error", bw);
				System.err.println("Invalid command from webservice");
		}
	}

	/**
	 * Used by the handle() method to return responses to the webservice.
	 * @param response Text to send.
	 * @param bw Writer for the socket connection.
	 * @throws IOException
	 */
	private void respond(String response, BufferedWriter bw) throws IOException {
		bw.write(response);
		bw.write(0);
		bw.flush();
	}
}
