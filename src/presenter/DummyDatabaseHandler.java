package presenter;

import model.Plant;
import model.PlantMonitor;
import model.User;

public class DummyDatabaseHandler implements IDatabaseHandler {
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
