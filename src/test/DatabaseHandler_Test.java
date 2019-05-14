package test;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.BasicDBObject;

import model.Account;
import model.PlantProfile;
import presenter.DatabaseHandler;

class DatabaseHandler_Test {

	String hostString; // initialize this to the real host string
	DatabaseHandler dbh;

	@BeforeEach
	void setUp() throws Exception {
		dbh = new DatabaseHandler(hostString);
	}

	@Test
	void getStatus() {
		// Status of Database connection
		String result = "";
		assertEquals(dbh.getStatus(), result);
	}

	@Test
	void getAccount() {
		Account account = new Account(); // initialize to an existing account
		String user = account.toJson();
		assertEquals(dbh.getAccount(user), account);
	}

	@Test
	void setAccount() {
		Account account = new Account(); // Set Account information, the account must be existing "Modification"
		assertEquals(dbh.setAccount(account), true);
	}

	@Test
	void addAccount() {
		Account account = new Account(); // Set Account information, Create a new one
		assertEquals(dbh.addAccount(account), true);
	}

	@Test
	void removeAccount() {
		String username = ""; // Set to existing username
		assertEquals(dbh.removeAccount(username), true);
	}

	@Test
	void setPlantProfile() {
		PlantProfile monitor = new PlantProfile();
		assertEquals(dbh.setPlantProfile(monitor), true);
	}

	@Test
	void getPlantProfile() {
		PlantProfile monitor = new PlantProfile(); // set to existing plant profile
		int plantID = monitor.PlantID;
		assertEquals(dbh.getPlantProfile(plantID), monitor);
	}

	@Test
	void removePlantProfile() {
		int plantID = 0; // Set to existing plant id
		assertEquals(dbh.removePlantProfile(plantID), true);

	}

}
