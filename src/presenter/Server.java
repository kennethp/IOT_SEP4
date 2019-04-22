package presenter;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class Server {
	static String host = "mongodb+srv://cluster0-7fj2z.mongodb.net";
	static String user = "test";
	static String pass = "plant123";

	static IDatabaseHandler databaseHandler;

	public static void main(String[] args) {
		String username = null;
		try {
			username = URLEncoder.encode("kennethpete@gmail.com", "UTF-8");
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String password = "12345678X!";
		databaseHandler = new DatabaseHandler(
				"mongodb+srv://" + username + ":" + password + "@cluster0-7fj2z.mongodb.net/test?retryWrites=true");
	}
}
