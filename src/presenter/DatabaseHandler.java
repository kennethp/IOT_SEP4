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

import javax.print.Doc;
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
		return set(user._id, Document.parse(user.toJson()), userCollection);
	}

	@Override
	public int addUser(User user) {
		return add(Document.parse(user.toJson()), userCollection);
	}

	@Override
	public boolean setPlantMonitor(PlantMonitor monitor) {
		return false;
	}

	@Override
	public Plant getPlant(int id) {
		Bson filter = new BasicDBObject("_id", id);
		Document document = plantCollection.find(filter).first();
		return Plant.fromJson(document.toJson());
	}

	@Override
	public boolean setPlant(Plant plant) {
		return set(plant._id, Document.parse(plant.toJson()), plantCollection);
	}

	@Override
	public int addPlant(Plant plant) {
		return add(Document.parse(plant.toJson()), plantCollection);
	}

	private int add(Document document, MongoCollection<Document> collection) {
		document.remove("_id");
		try {
			collection.insertOne(document);
			return 0;
		} catch(MongoWriteException e) {
			return 1;
		} catch(MongoWriteConcernException f) {
			return 2;
		} catch(MongoException g) {
			return 3;
		}
	}

	private boolean set(int id, Document update, MongoCollection<Document> collection) {
		Bson filter = new BasicDBObject("_id", id);
		try {
			collection.findOneAndReplace(filter, update);
		} catch(Exception e) {
			return false;
		}
		return true;
	}
}
