package view;

import presenter.Server;
import presenter.TlsSocketFactory;
import test.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Server server = new Server();
		try {
			Thread.sleep(100);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		Client client = new Client(TlsSocketFactory.getInstance().getTestClientSocket(3001));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				String s = br.readLine();
				System.out.println("--\nSending: " + s + "\n--");
				client.write(s);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
