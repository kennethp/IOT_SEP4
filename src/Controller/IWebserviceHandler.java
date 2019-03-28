package Controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Model.Plant;
import Model.PlantMonitor;
import Model.User;

public interface IWebserviceHandler {
	
	public User getUser(int id);
	public int addUser(User user);
	public boolean removeUser(int id);
	public Plant getPlant (int id);
	public int addPlant(Plant plant);
	public boolean removePlant(int id);
	public PlantMonitor getPlantMonitor(int id);

}
