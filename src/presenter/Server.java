package presenter;

import model.User;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
	static String host = "@test-i10mg.mongodb.net/test?retryWrites=true";
	static String user = "Llamaxy";
	static String pass = "865feeBA";

	IDatabaseHandler databaseHandler;
	WebserviceConnector webserviceConnector;
	IWebserviceHandler webserviceHandler;

	ExecutorService executorService;

	public Server() {
		executorService = new ScheduledThreadPoolExecutor(2);

		databaseHandler = new DatabaseHandler("mongodb+srv://" + user + ':' + pass + host);
		webserviceHandler = new WebserviceHandler(databaseHandler);
		webserviceConnector = new WebserviceConnector(webserviceHandler, 3001);
		executorService.submit(webserviceConnector);
	}
}
