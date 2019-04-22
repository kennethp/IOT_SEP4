package presenter;

import model.User;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class Server {
	static String host = "mongodb+srv://cluster0-7fj2z.mongodb.net";
	static String user = "test";
	static String pass = "plant123";

	static IDatabaseHandler databaseHandler;

	public static void main(String[] args) {
		databaseHandler = new DatabaseHandler(
				"mongodb+srv://Llamaxy:865feeBA@test-i10mg.mongodb.net/test?retryWrites=true");


	}
}
