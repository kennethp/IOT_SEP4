package presenter;

import model.Plant;
import model.PlantMonitor;
import model.User;

public class WebserviceHandler implements IWebserviceHandler {
	IDatabaseHandler databaseHandler;

	public WebserviceHandler(IDatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	@Override
	public User getUser(int id) {
		return null;
	}

	@Override
	public int addUser(User user) {
		return 0;
	}

	@Override
	public boolean modifyUser(User user) {
		return false;
	}

	@Override
	public boolean removeUser(int id) {
		return false;
	}

	@Override
	public Plant getPlant(int id) {
		return databaseHandler.getPlant(id);
	}

	@Override
	public int addPlant(Plant plant) {
		return 0;
	}

	@Override
	public boolean modifyPlant(Plant plant) {
		return false;
	}

	@Override
	public boolean removePlant(int id) {
		return false;
	}

	@Override
	public PlantMonitor getPlantMonitor(int id) {
		return null;
	}
}
