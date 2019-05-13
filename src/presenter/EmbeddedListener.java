package presenter;

import model.PlantProfile;
import org.bson.Document;
import org.bson.conversions.Bson;

import static java.lang.System.exit;

public class EmbeddedListener implements Runnable{
	IEmbeddedConnector wsl;
	IDatabaseHandler databaseHandler;
	String name;
	int id;

	public EmbeddedListener(IDatabaseHandler databaseHandler) {
		wsl = new WebSocketListener();
		this.databaseHandler = databaseHandler;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
				exit(1);
			}
			String update = wsl.getMessage();
			if(update != null) {
				char[] hex = Document.parse(update).getString("data").toCharArray();
				int hum = Integer.parseInt("" + hex[0] + hex[1], 16);
				int temp = Integer.parseInt("" + hex[2] + hex[3], 16);
				int co2 = Integer.parseInt("" + hex[4] + hex[5] + hex[6] + hex[7], 16);
				int light = Integer.parseInt("" + hex[8] + hex[9], 16);
				int water = Integer.parseInt("" + hex[10] + hex[11], 16);

				PlantProfile pp = new PlantProfile();
				pp.AmountOfWater = 0;
				pp.CO2 = co2;
				pp.Humidity = hum;
				pp.HoursSinceWatering = water;
				pp.Light = light;
				pp.Temperature = temp;
				pp.PlantName = name;
				pp.PlantID = id;

				//TODO submit to database
			}
		}
	}
}
