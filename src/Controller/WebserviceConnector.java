package Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class WebserviceConnector implements Runnable {
	IWebserviceHandler webserviceHandler = new DummyWebserviceHandler();
	int port;

	public WebserviceConnector(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		try {
			listen(port);
		} catch(IOException e) {
			System.exit(1);
		}
	}

	private void listen(int port) throws IOException {
		ServerSocket ss = new ServerSocket(port);
		Socket socket;
		while(true) {
			socket = ss.accept();
			InputStreamReader ir = new InputStreamReader(socket.getInputStream());
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			ByteArrayOutputStream baos;
			while(!socket.isClosed()) {
				baos = new ByteArrayOutputStream();

				int c;
				while((c = ir.read()) != 0) {
					baos.write(c);
				}

				handle(new String(baos.toByteArray(), Charset.forName("UTF-8")), bw);
				baos.reset();
			}
		}
	}

	private void handle(String input, BufferedWriter bw) throws IOException {
		int c = input.indexOf(':');
		String cmd = input.substring(0, c);
		String param = input.substring(c+1, input.length());

		switch(cmd) {
			case "getuser":
				int id;
				try {
					id = Integer.parseInt(param);
				} catch(NumberFormatException e) {
					id = -1;
				}
				respond(webserviceHandler.getUser(id).toJson(), bw);
		}
	}

	private void respond(String response, BufferedWriter bw) throws IOException {
		bw.write(response);
		bw.flush();
	}
}
