package view;

import presenter.Server;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
	public static void main(String[] args) {
		Server server = new Server(3001);
		Scanner scanner = new Scanner(System.in);

		String input;
		while(!(input = scanner.nextLine()).equals("exit")) {
			switch(input) {
				case "help":
					System.out.println("Commands: ");
					System.out.println(" help");
					System.out.println(" status");
					System.out.println(" exit");
					break;
				case "status":
					System.out.println(server.getStatus());
					break;
				default:
					System.out.println("? Try 'help'");
			}
		}

		System.out.println("Shutting down");
		exit(0);
	}
}
