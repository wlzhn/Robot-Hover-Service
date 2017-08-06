package com.robot.dao;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

/**
 * Generate connection to MongoDB.
 */
public class MongoDBConnectionFactory {
	
    private static MongoClient instance;
    
    private MongoDBConnectionFactory(){}
    
    public static MongoClient getInstance(String url, int port) 
    		throws UnknownHostException{
        if(instance == null) {
        	synchronized (MongoDBConnectionFactory.class) {
        		if (instance == null)
        			instance = new MongoClient(url, port);
        	}
        }
        return instance;
    }
	    
	
}
