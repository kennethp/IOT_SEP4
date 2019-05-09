package presenter;

import model.Account;
import model.PlantProfile;

public interface IDatabaseHandler {
	
	public Account getAccount(String user);
	public boolean setAccount(Account user);
	public boolean addAccount(Account user);
	public boolean setPlantProfile(PlantProfile monitor);
	public PlantProfile getPlantProfile(int id);
	public String getStatus();
}
