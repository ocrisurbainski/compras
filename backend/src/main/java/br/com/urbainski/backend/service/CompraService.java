package br.com.urbainski.backend.service;

import static br.com.urbainski.backend.util.MongoDBDatabase.COMPRAS_COLLECTION;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.mongodb.client.MongoCollection;

import br.com.urbainski.backend.entity.Compra;
import br.com.urbainski.backend.entity.enums.SituacaoEntrega;
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
public class CompraService extends AbstractService {

	public Compra save(Compra compra) {
		compra.setSituacaoEntrega(SituacaoEntrega.AGUARDANDO);
		getCollection().insertOne(compra);
		return compra;
	}

	public PageResponse<Compra> findAll(PageRequest page) {
		MongoCollection<Compra> mongoCollection = getCollection();
		long total = mongoCollection.countDocuments();
		List<Compra> compras = new Pagination<Compra>().applyPagination(page, mongoCollection.find());
		return new PageResponse<Compra>(total, compras);
	}

	public MongoCollection<Compra> getCollection() {
		return getMongoDatabase().getCollection(COMPRAS_COLLECTION, Compra.class);
	}

}