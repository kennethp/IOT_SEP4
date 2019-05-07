package test;

import presenter.IWebserviceHandler;
import presenter.Server;
import presenter.WebserviceConnector;

import java.util.Scanner;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TestServer {
	public static void main(String[] args) {
		IWebserviceHandler ws = new TestWebserviceHandler();
		WebserviceConnector connector = new WebserviceConnector(ws, 3001);
		Server.executorService = new ScheduledThreadPoolExecutor(2);
		Server.executorService.submit(connector);
		System.out.println("Running");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		System.out.println("Server killed");
		connector.kill();
	}
}
