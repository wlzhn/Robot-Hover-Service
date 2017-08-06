package com.robot.dao;

import java.net.UnknownHostException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.robot.model.Input;
import com.robot.model.Output;

public class MongoDBWriterImplTest {
	
	private static Input input;
	
	private static Output output;
	
	/*@Test
	public void testWriteInputToDatabase() throws UnknownHostException {
		MongoDBConnectorImpl dbConnector = new MongoDBConnectorImpl();
		dbConnector.writeToDatabase(input);
	}
	
	@Test
	public void testWriteOutputToDatabase() throws UnknownHostException {
		MongoDBConnectorImpl dbConnector = new MongoDBConnectorImpl();
		dbConnector.writeToDatabase(output);
	}*/
	
	@Test
	public void testRetrieveAll() throws UnknownHostException {
		String url = "localhost";
		int port = 27017;
		String dbName = "testDB";
		String colName = "testCol";
		MongoDBWriterImpl dbConnector = new MongoDBWriterImpl(url, port, dbName, colName);
		//dbConnector.retrieveAll();
	}
}
