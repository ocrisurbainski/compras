package br.com.urbainski.backend.service;

import static br.com.urbainski.backend.util.MongoDBDatabase.CLIENTES_COLLECTION;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import br.com.urbainski.backend.entity.Cliente;
import br.com.urbainski.backend.pagination.PageRequest;
import br.com.urbainski.backend.pagination.PageResponse;
import br.com.urbainski.backend.pagination.Pagination;

/**
 * 
 * @author Cristian Urbainski
 * @since 27/09/2019
 *
 */
@ApplicationScoped
public class ClienteService extends AbstractService {

	public Cliente save(Cliente cliente) {
		getCollection().insertOne(cliente);
		return cliente;
	}

	public Cliente update(Cliente cliente) {
		Bson filter = getFilterById(cliente.getId());
		UpdateResult result = getCollection().replaceOne(filter, cliente);
		if (result.getModifiedCount() == 0L) {
			throw new NotFoundException();
		}
		return cliente;
	}

	public void delete(String id) {
		Bson filter = getFilterById(id);
		DeleteResult result = getCollection().deleteOne(filter);
		if (result.getDeletedCount() == 0L) {
			throw new NotFoundException();
		}
	}

	public Cliente findOne(String id) {
		Bson filter = getFilterById(id);
		Cliente cliente = getCollection().find(filter).first();
		return cliente;
	}

	public PageResponse<Cliente> findAll(PageRequest page) {
		MongoCollection<Cliente> mongoCollection = getCollection();
		long total = mongoCollection.countDocuments();
		List<Cliente> clientes = new Pagination<Cliente>().applyPagination(page, mongoCollection.find());
		return new PageResponse<Cliente>(total, clientes);
	}

	public MongoCollection<Cliente> getCollection() {
		return getMongoDatabase().getCollection(CLIENTES_COLLECTION, Cliente.class);
	}

	private Bson getFilterById(String id) {
		return Filters.eq("_id", id);
	}

}