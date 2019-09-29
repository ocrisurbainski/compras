package br.com.urbainski.backend.service;

import static br.com.urbainski.backend.util.MongoDBDatabase.PRODUTOS_COLLECTION;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import br.com.urbainski.backend.entity.Produto;
import br.com.urbainski.backend.pagination.PageRequest;
import br.com.urbainski.backend.pagination.PageResponse;
import br.com.urbainski.backend.pagination.Pagination;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
@ApplicationScoped
public class ProdutoService extends AbstractService {

	public Produto save(Produto produto) {
		getCollection().insertOne(produto);
		return produto;
	}

	public Produto update(Produto produto) {
		Bson filter = getFilterById(produto.getId());
		UpdateResult result = getCollection().replaceOne(filter, produto);
		if (result.getModifiedCount() == 0L) {
			throw new NotFoundException();
		}
		return produto;
	}

	public void delete(String id) {
		Bson filter = getFilterById(id);
		DeleteResult result = getCollection().deleteOne(filter);
		if (result.getDeletedCount() == 0L) {
			throw new NotFoundException();
		}
	}

	public Produto findOne(String id) {
		Bson filter = getFilterById(id);
		Produto produto = getCollection().find(filter).first();
		return produto;
	}

	public PageResponse<Produto> findAll(PageRequest page) {
		MongoCollection<Produto> mongoCollection = getCollection();
		long total = mongoCollection.countDocuments();
		List<Produto> produtos = new Pagination<Produto>().applyPagination(page, mongoCollection.find());
		return new PageResponse<Produto>(total, produtos);
	}

	public MongoCollection<Produto> getCollection() {
		return getMongoDatabase().getCollection(PRODUTOS_COLLECTION, Produto.class);
	}

	private Bson getFilterById(String id) {
		return Filters.eq("_id", id);
	}
	
}