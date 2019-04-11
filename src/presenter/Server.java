package presenter;

public class Server {
	static String host = "mongodb+srv://cluster0-7fj2z.mongodb.net";
	static String user = "test";
	static String pass = "plant123";

	static IDatabaseHandler databaseHandler;

	public static void main(String[] args) {
		databaseHandler = new DatabaseHandler(
				"mongodb+srv://test:UkDR9LkYL7V708LV@cluster0-7fj2z.mongodb.net/test?retryWrites=true");
		databaseHandler.getUser(1);
	}
}
