package presenter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

import model.Plant;
import model.User;

import com.fasterxml.jackson.core.JsonParseException;
import sun.security.provider.X509Factory;

import javax.net.ssl.*;

import static java.lang.System.exit;

public class WebserviceConnector implements Runnable {
	IWebserviceHandler webserviceHandler = new DummyWebserviceHandler();
	int port;
	static Charset charset = Charset.forName("UTF-8");

	public WebserviceConnector(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		try {
			listen(port);
		} catch (IOException e) {
			exit(1);
		}
	}

	private void listen(int port) throws IOException {
		ServerSocket ss = TlsSocketFactory.getInstance().getServerSocket(port);

		Socket socket;
		while (true) {
			socket = ss.accept();
			InputStreamReader ir = new InputStreamReader(socket.getInputStream());
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (!socket.isClosed()) {
				int c;
				while ((c = ir.read()) != 0) {
					baos.write(c);
				}

				handle(new String(baos.toByteArray(), charset), bw);
				baos.reset();
			}
		}
	}

	private void handle(String input, BufferedWriter bw) throws IOException {
		int c = input.indexOf(':');
		String cmd = input.substring(0, c);
		String param = input.substring(c + 1, input.length());

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
		}

	}

	private void respond(String response, BufferedWriter bw) throws IOException {
		bw.write(response);
		bw.write(0);
		bw.flush();
	}

}
