package presenter;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import model.Plant;
import model.PlantMonitor;
import model.User;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.function.Consumer;


public class DatabaseHandler implements IDatabaseHandler {
	MongoClient mongo;
	MongoDatabase database;
	MongoCollection<Document> userCollection;
	MongoCollection<Document> plantCollection;

	/**
	 * Constructs a database handler internally using com.mongodb.MongoClient
	 * @param hostString MongoClientURI string
	 */
	public DatabaseHandler(String hostString) {
		mongo = MongoClients.create(hostString);
		database = mongo.getDatabase("Project");
		plantCollection = database.getCollection("Plants");

		System.out.println(plantCollection.find().first().toJson());
//		userCollection = database.getCollection("Test");
	}

	/**
	 * Returns a User fetched from database
	 * @param id Users id in database
	 * @return User object
	 */
	@Override
	public User getUser(int id) {
		Bson filter = new BasicDBObject("_id", id);
		Document document = userCollection.find(filter).first();
		return User.fromJson(document.toJson());
	}

	@Override
	public boolean setUser(User user) {

		Bson filter = new BasicDBObject("_id", user._id);
		Document update = Document.parse(user.toJson());
		userCollection.findOneAndReplace(filter, update);
		return false;
	}

	@Override
	public int addUser(User user) {
		Document document = Document.parse(user.toJson());
		document.remove("_id");
		try {
			userCollection.insertOne(document);
			return 0;
		} catch(MongoWriteException e) {
			return 1;
		} catch(MongoWriteConcernException f) {
			return 2;
		} catch(MongoException g) {
			return 3;
		}
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
