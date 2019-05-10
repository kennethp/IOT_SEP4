package presenter;

import model.Account;
import model.PlantProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Establishes bridge application
 */
public class Server {
	static String host = "@test-i10mg.mongodb.net/test?retryWrites=true";
	static String user = "Llamaxy";
	static String pass = "865feeBA";

	public IDatabaseHandler databaseHandler;
	public WebserviceConnector webserviceConnector;
	public IWebserviceHandler webserviceHandler;
	public EmbeddedListener embeddedListener;

	public static ExecutorService executorService;

	/**
	 * Establishes the bridge application
	 * @param port Port webservice socket
	 */
	public Server(int port) {
		executorService = new ScheduledThreadPoolExecutor(4);

		databaseHandler = new DatabaseHandler("mongodb+srv://" + user + ':' + pass + host);
		webserviceHandler = new WebserviceHandler(databaseHandler);
		webserviceConnector = new WebserviceConnector(webserviceHandler, port);
		embeddedListener = new EmbeddedListener(databaseHandler);

		executorService.submit(webserviceConnector);
		executorService.submit(embeddedListener);
	}

	/**
	 * Used by CLI to get status
	 * @return Status String
	 */
	public String getStatus() {
		return webserviceConnector.STATUS
				+ '\n'
				+ databaseHandler.getStatus();
	}
}
