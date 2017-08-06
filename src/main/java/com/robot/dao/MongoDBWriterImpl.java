package com.robot.dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.robot.model.Input;
import com.robot.model.Output;

/**
 * Writes inputs and outputs to MongoDB.
 */
public class MongoDBWriterImpl implements DatabaseWriter {
	
	private String dbName;
	private String colName;
	private MongoClient mongoClient;
	
	public MongoDBWriterImpl(String url, int port, String dbName, String colName) throws UnknownHostException {
		mongoClient = MongoDBConnectionFactory.getInstance(url, port);
		this.dbName = dbName;
		this.colName = colName;
	}
	
	public void writeToDatabase(Input input) {
		DB db = mongoClient.getDB(dbName);
		DBCollection col = db.getCollection(colName);
		col.save(createInputDBObject(input));
	}
	
	public void writeToDatabase(Output output) {
		DB db = mongoClient.getDB(dbName);
		DBCollection col = db.getCollection(colName);
		col.save(createOutputDBObject(output));
	}
	
	BasicDBObject createInputDBObject(Input input) {
		BasicDBObject dbObj = new BasicDBObject();
		if (input.getRoomSize() != null)
			dbObj.append("roomSize", input.getRoomSize());
		if (input.getCoords() != null)
			dbObj.append("coords", input.getCoords());
		if (input.getPatches() != null)
			dbObj.append("patches", input.getPatches());
		if (input.getInstructions() != null)
			dbObj.append("instructions", input.getInstructions());
		return dbObj;
	}
	
	BasicDBObject createOutputDBObject(Output output) {
		BasicDBObject dbObj = new BasicDBObject();
		dbObj.append("coords", output.getCoords());
		dbObj.append("patches", output.getPatches());
		return dbObj;
	}
}
