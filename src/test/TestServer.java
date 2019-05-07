package test;

import presenter.IWebserviceHandler;
import presenter.WebserviceConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TestServer {
	public static void main(String[] args) {
		IWebserviceHandler ws = new TestWebserviceHandler();
		WebserviceConnector connector = new WebserviceConnector(ws, 3001);
		ExecutorService executorService = new ScheduledThreadPoolExecutor(2);
		executorService.submit(connector);
		System.out.println("Running");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		connector.kill();
		System.out.println("Server is dead");
	}
}
