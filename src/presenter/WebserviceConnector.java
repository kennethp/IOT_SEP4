package presenter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import static java.lang.System.exit;

/**
 * Connection handler for the communication with the Webservice
 */
public class WebserviceConnector implements Runnable {
	IWebserviceHandler webserviceHandler;
	int port;
	static Charset charset = Charset.forName("UTF-8");
	boolean die = false;
	ServerSocket ss;
	public String STATUS = "No client connected";

	public WebserviceConnector(IWebserviceHandler webserviceHandler, int port) {
		this.webserviceHandler = webserviceHandler;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			listen(port);
		} catch (IOException e) {
			e.printStackTrace();
			exit(1);
		}
	}

	public void kill() {
		exit(0);
	}

	/**
	Listens for connection requests from the client and establishes a socket
	connection. If the socket closes, it will go back to listening.
	 It reads text from the socket connection until it receives a 0 byte
	 and then passes the string to the handle method.
	@param port Port of the socket connection.
	 */
	private void listen(int port) throws IOException {
		ss = TlsSocketFactory.getInstance().getServerSocket(port);

		Socket socket;
		SocketReader sr = null;
		while (true) {
			System.out.println("Listening for new connection");
			socket = ss.accept();
			STATUS = "Client is connected";

			if(sr != null) {
				sr.kill();
			}

			sr = new SocketReader(socket, webserviceHandler);
			Server.executorService.submit(sr);
		}
	}
}