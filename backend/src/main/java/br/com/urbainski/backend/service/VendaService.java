package br.com.urbainski.backend.service;

import static br.com.urbainski.backend.util.MongoDBDatabase.VENDAS_COLLECTION;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.mongodb.client.MongoCollection;

import br.com.urbainski.backend.entity.Venda;
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
public class VendaService extends AbstractService {

	public Venda save(Venda venda) {
		venda.setSituacaoEntrega(SituacaoEntrega.AGUARDANDO);
		getCollection().insertOne(venda);
		return venda;
	}

	public PageResponse<Venda> findAll(PageRequest page) {
		MongoCollection<Venda> mongoCollection = getCollection();
		long total = mongoCollection.countDocuments();
		List<Venda> vendas = new Pagination<Venda>().applyPagination(page, mongoCollection.find());
		return new PageResponse<Venda>(total, vendas);
	}

	public MongoCollection<Venda> getCollection() {
		return getMongoDatabase().getCollection(VENDAS_COLLECTION, Venda.class);
	}

}