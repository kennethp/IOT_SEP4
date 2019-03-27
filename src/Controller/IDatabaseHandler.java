package Controller;

import Model.Plant;
import Model.PlantMonitor;
import Model.User;

public interface IDatabaseHandler {
	
	public User getUser(int id);
	public boolean setUser(User user);
	public int addUser(User user);
	public boolean setPlantMonitor(PlantMonitor monitor);
	public Plant getPlant(int id);
	public boolean setPlant(Plant plant);
	public int addPlant(Plant plant);

}
