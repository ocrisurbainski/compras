package br.com.urbainski.backend.service;

import static br.com.urbainski.backend.util.MongoDBDatabase.DATABASE;

import javax.inject.Inject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author Cristian Urbainski
 * @since 28/09/2019
 *
 */
public abstract class AbstractService {

	@Inject
	protected MongoClient mongoClient;
	
	private MongoDatabase mongoDatabase;
	
	public MongoDatabase getMongoDatabase() {
		if (mongoDatabase == null) {
			mongoDatabase = mongoClient.getDatabase(DATABASE);
		}
		return mongoDatabase;
	}
}
