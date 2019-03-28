package presenter;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Plant;
import model.PlantMonitor;
import model.User;

public interface IWebserviceHandler {
	
	public User getUser(int id);
	public int addUser(User user);
	public boolean removeUser(int id);
	public Plant getPlant (int id);
	public int addPlant(Plant plant);
	public boolean removePlant(int id);
	public PlantMonitor getPlantMonitor(int id);

}
