package test;


import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;

import model.Account;
import model.PlantProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presenter.DatabaseHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DatabaseHandler_Test {

	String hostString = "mongodb+srv://Llamaxy:865feeBA@test-i10mg.mongodb.net/test?retryWrites=true";
	DatabaseHandler dbh;
	Account account = new Account();
	PlantProfile monitor = new PlantProfile();

	@BeforeEach
	void setUp() throws Exception {
		dbh = new DatabaseHandler(hostString);
	}

	@Test
	void getStatus() {
		String status = "Connected to mongoDB";
		assertEquals(dbh.getStatus(), status);
	}

	@Test
	void addAccount() {
		account.Username = "Test_name";
		account.Password = "Test_password";
		assertEquals(dbh.addAccount(account), true);
	}

	@Test
	void getAccount() {
		String user = account.toJson();
		assertEquals(dbh.getAccount(user), account);
	}

	@Test
	void setAccount() {
		account.Password = "new_Test_Password";
		assertEquals(dbh.setAccount(account), true);
	}

	@Test
	void removeAccount() {
		String username = account.Username;
		assertEquals(dbh.removeAccount(username), true);
	}

	@Test
	void setPlantProfile() {
		assertEquals(dbh.setPlantProfile(monitor), true);
	}

	@Test
	void getPlantProfile() {
		int plantID = monitor.PlantID;
		assertEquals(dbh.getPlantProfile(plantID), monitor);
	}

	@Test
	void removePlantProfile() {
		int plantID = monitor.PlantID;
		assertEquals(dbh.removePlantProfile(plantID), true);

	}

}
