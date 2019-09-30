package br.com.urbainski.backend.mapper;

import org.bson.Document;
import org.bson.types.Decimal128;

import br.com.urbainski.backend.entity.Produto;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public abstract class ProdutoMapper {

	public static Document toDocument(Produto produto) {
		Document doc = new Document();
		doc.put(Produto.Campos._id.name(), produto.getId());
		doc.put(Produto.Campos.nome.name(), produto.getNome());
		doc.put(Produto.Campos.marca.name(), produto.getMarca());
		doc.put(Produto.Campos.valorVenda.name(), produto.getValorVenda());
		return doc;
	}

	public static Produto toEntity(Document document) {
		Decimal128 valorVenda = document.get(Produto.Campos.valorVenda.name(), Decimal128.class);
		
		Produto produto = new Produto();
		produto.setId(document.getString(Produto.Campos._id.name()));
		produto.setNome(document.getString(Produto.Campos.nome.name()));
		produto.setMarca(document.getString(Produto.Campos.marca.name()));
		produto.setValorVenda(valorVenda.bigDecimalValue());
		return produto;
	}

}