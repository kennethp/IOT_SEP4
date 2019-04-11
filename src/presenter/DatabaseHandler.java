package presenter;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import model.Plant;
import model.PlantMonitor;
import model.User;
import org.bson.Document;

import java.util.function.Consumer;


public class DatabaseHandler implements IDatabaseHandler {
	MongoClient mongo;
	MongoDatabase database;

	/**
	 * Constructs a database handler internally using com.mongodb.MongoClient
	 * @param hostString MongoClientURI string
	 */
	public DatabaseHandler(String hostString) {
		MongoClient mongoClient = new MongoClient(new MongoClientURI(hostString));
		mongoClient.startSession();
		mongoClient.listDatabaseNames().forEach(new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println("Database");
				System.out.println(s);
			}
		});
		MongoDatabase database = mongoClient.getDatabase("test");
	}

	/**
	 * Returns a User fetched from database
	 * @param id Users id in database
	 * @return User object
	 */
	@Override
	public User getUser(int id) {
		Document d = database.getCollection("Test").find().first();
		System.out.println(d.toString());
		return null;
	}

	@Override
	public boolean setUser(User user) {
		return false;
	}

	@Override
	public int addUser(User user) {
		return 0;
	}

	@Override
	public boolean setPlantMonitor(PlantMonitor monitor) {
		return false;
	}

	@Override
	public Plant getPlant(int id) {
		return null;
	}

	@Override
	public boolean setPlant(Plant plant) {
		return false;
	}

	@Override
	public int addPlant(Plant plant) {
		return 0;
	}
}
