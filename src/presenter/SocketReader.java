package presenter;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.net.Socket;
import model.*;

import static java.lang.System.exit;

public class SocketReader implements Runnable {
	Socket socket;
	IWebserviceHandler webserviceHandler;
	Thread t;
	boolean auth = false;
	String authToken = "123123";

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

		switch (cmd) {
			case "getuser":
				int id_getUser;
				try {
					id_getUser = Integer.parseInt(param);
				} catch (NumberFormatException e) {
					id_getUser = -1;
				}
				respond(webserviceHandler.getUser(id_getUser).toJson(), bw);
				break;

			case "adduser":
				User userToAdd = User.fromJson(param);
				String response = Integer.toString(webserviceHandler.addUser(userToAdd));
				respond(response, bw);
				break;
			case "modifyuser":
				User userToModify = User.fromJson(param);

				if (webserviceHandler.modifyUser(userToModify)) {
					respond("True", bw);
				} else {
					respond("False", bw);
				}
				break;

			case "removeuser":
				int id_RemoveUser;
				try {
					id_RemoveUser = Integer.parseInt(param);
				} catch (NumberFormatException e) {
					id_RemoveUser = -1;
				}
				if (webserviceHandler.removeUser(id_RemoveUser)) {
					respond("True", bw);
				} else {
					respond("False", bw);
				}
				break;

			case "getplant":
				int id_getPlant;
				try {
					id_getPlant = Integer.parseInt(param);
				} catch (NumberFormatException e) {
					id_getPlant = -1;
				}
				respond(webserviceHandler.getPlant(id_getPlant).toJson(), bw);
				break;

			case "addplant":

				Plant plantToAdd = Plant.fromJson(param);
				String res = Integer.toString(webserviceHandler.addPlant(plantToAdd));
				respond(res, bw);
				break;

			case "modifyplant":
				Plant plantToModify = Plant.fromJson(param);

				if (webserviceHandler.modifyPlant(plantToModify)) {
					respond("True", bw);
				} else {
					respond("False", bw);
				}
				break;

			case "removeplant":

				int id_RemovePlant;
				try {
					id_RemovePlant = Integer.parseInt(param);
				} catch (NumberFormatException e) {
					id_RemovePlant = -1;
				}
				if (webserviceHandler.removePlant(id_RemovePlant)) {
					respond("True", bw);
				} else {
					respond("False", bw);
				}
				break;

			case "getplantmonitor":
				int id_getplantmonitor;
				try {
					id_getplantmonitor = Integer.parseInt(param);
				} catch (NumberFormatException e) {
					id_getplantmonitor = -1;
				}
				respond(webserviceHandler.getPlantMonitor(id_getplantmonitor).toJson(), bw);
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
