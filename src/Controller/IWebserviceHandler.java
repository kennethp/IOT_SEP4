package Controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Model.Plant;
import Model.PlantMonitor;
import Model.User;

public interface IWebserviceHandler extends Remote {
	
	public User getUser(int id) throws RemoteException;
	public Plant getPlant (int id) throws RemoteException;
	public PlantMonitor getPlantMonitor(int id) throws RemoteException;

}
