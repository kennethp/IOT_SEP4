package presenter;

import model.Plant;
import model.PlantMonitor;
import model.User;

public class DummyWebserviceHandler implements IWebserviceHandler {
	@Override
	public User getUser(int id) {
		return null;
	}

	@Override
	public int addUser(User user) {
		return 0;
	}

	@Override
	public boolean removeUser(int id) {
		return false;
	}

	@Override
	public Plant getPlant(int id) {
		return null;
	}

	@Override
	public int addPlant(Plant plant) {
		return 0;
	}

	@Override
	public boolean removePlant(int id) {
		return false;
	}

	@Override
	public PlantMonitor getPlantMonitor(int id) {
		return null;
	}

	@Override
	public boolean modifyUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyPlant(Plant plant) {
		// TODO Auto-generated method stub
		return false;
	}
}
