package test;

import presenter.TlsSocketFactory;

import java.io.*;
import java.net.Socket;

public class Client {
	Socket socket;

	public Client(Socket socket) {
		this.socket = socket;
	}

	public void write(String input) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		bw.write(input + '\0');
		bw.flush();
		System.out.println("Client: Input written");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int c;
		int n = 0;
		while((c = br.read()) != 0) {
			if(c == -1) {
				break;
			}
			System.out.println(">Client: " + n++ + " characters read from stream");
			System.out.println();
			baos.write(c);
		}
		String response = new String(baos.toByteArray(), "UTF-8");
		System.out.println(response);
	}

	public static void main(String[] args) {
		Client client = new Client(TlsSocketFactory.getInstance().getTestClientSocket(3001));
		try {
			client.write("getplant:1");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
