package br.com.urbainski.backend.service;

import static br.com.urbainski.backend.util.MongoDBDatabase.COMPRAS_COLLECTION;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

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
	
	public void updateSituacaoEntrega(String id, SituacaoEntrega situacaoEntrega) {
		if (StringUtils.isEmpty(id)) {
			throw new IllegalArgumentException("Id deve ser informado.");
		}
		if (situacaoEntrega == null) {
			throw new IllegalArgumentException("Situação de entrega deve ser informado.");
		}
		Bson filter = getFilterById(id);
		
		Document prop = new Document();
		prop.put(Compra.Campos.situacaoEntrega.name(), situacaoEntrega.name());
		
		Document update = new Document();
		update.put("$set", prop);
		
		getCollection().updateOne(filter, update);
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
	
	private Bson getFilterById(String id) {
		return Filters.eq("_id", id);
	}

}