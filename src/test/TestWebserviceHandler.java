package test;

import model.Plant;
import model.PlantMonitor;
import model.User;
import presenter.IWebserviceHandler;

public class TestWebserviceHandler implements IWebserviceHandler {

	public TestWebserviceHandler() {
		System.out.println("Handler started");
	}

	@Override
	public User getUser(int id) {
		User u = new User();
		u.username = "John";
		u.password = "asdf123";
		u.email = "john@gmail.com";
		u.name = "John Johnson";
		u.id = 1;
		return u;
	}

	@Override
	public int addUser(User user) {
		return 0;
	}

	@Override
	public boolean modifyUser(User user) {
		return true;
	}

	@Override
	public boolean removeUser(int id) {
		return true;
	}

	@Override
	public Plant getPlant(int id) {
		Plant p = new Plant();
		p.name = "Cactus";
		p.assignedProfile = 1;
		p.id = 1;
		return p;
	}

	@Override
	public int addPlant(Plant plant) {
		return 0;
	}

	@Override
	public boolean modifyPlant(Plant plant) {
		return true;
	}

	@Override
	public boolean removePlant(int id) {
		return true;
	}

	@Override
	public PlantMonitor getPlantMonitor(int id) {
		PlantMonitor p = new PlantMonitor();
		p.CO2 = 520;
		p.humidity = 50;
		p.temperature = 24;
		p.wateringPeriod = 1;
		return p;
	}
}
