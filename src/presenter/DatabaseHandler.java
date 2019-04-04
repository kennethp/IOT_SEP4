package presenter;

import com.mongodb.MongoClient;
import model.Plant;
import model.PlantMonitor;
import model.User;

public class DatabaseHandler implements IDatabaseHandler {
	MongoClient mongo;

	public DatabaseHandler(String host, int port) {
		mongo = new MongoClient(host, port);
	}

	@Override
	public User getUser(int id) {
		return null;
	}

	@Override
	public boolean setUser(User user) {
		return false;
	}

	@Override
	public int addUser(User user) {
		return 0;
	}

	@Override
	public boolean setPlantMonitor(PlantMonitor monitor) {
		return false;
	}

	@Override
	public Plant getPlant(int id) {
		return null;
	}

	@Override
	public boolean setPlant(Plant plant) {
		return false;
	}

	@Override
	public int addPlant(Plant plant) {
		return 0;
	}
}
