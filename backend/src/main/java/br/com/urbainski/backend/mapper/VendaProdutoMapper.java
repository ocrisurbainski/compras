package br.com.urbainski.backend.mapper;

import org.bson.Document;
import org.bson.types.Decimal128;

import br.com.urbainski.backend.entity.VendaProduto;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public abstract class VendaProdutoMapper {

	public static Document toDocument(VendaProduto vendaProduto) {
		Document doc = new Document();
		doc.put(VendaProduto.Campos.quantidade.name(), vendaProduto.getQuantidade());
		doc.put(VendaProduto.Campos.valorVenda.name(), vendaProduto.getValorVenda());
		doc.put(VendaProduto.Campos.valorTotal.name(), vendaProduto.getValorTotal());
		doc.put(VendaProduto.Campos.produto.name(), ProdutoMapper.toDocument(vendaProduto.getProduto()));
		return doc;
	}

	public static VendaProduto toEntity(Document document) {
		Decimal128 quantidade = document.get(VendaProduto.Campos.quantidade.name(), Decimal128.class);
		Decimal128 valorVenda = document.get(VendaProduto.Campos.valorVenda.name(), Decimal128.class);
		Decimal128 valorTotal = document.get(VendaProduto.Campos.valorTotal.name(), Decimal128.class);

		Document docProduto = document.get(VendaProduto.Campos.produto.name(), Document.class);
		VendaProduto vendaProduto = new VendaProduto();
		vendaProduto.setQuantidade(quantidade.bigDecimalValue());
		vendaProduto.setValorVenda(valorVenda.bigDecimalValue());
		vendaProduto.setValorTotal(valorTotal.bigDecimalValue());
		vendaProduto.setProduto(ProdutoMapper.toEntity(docProduto));
		return vendaProduto;
	}
}