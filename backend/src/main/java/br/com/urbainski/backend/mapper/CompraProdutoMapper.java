package br.com.urbainski.backend.mapper;

import org.bson.Document;
import org.bson.types.Decimal128;

import br.com.urbainski.backend.entity.CompraProduto;
import br.com.urbainski.backend.entity.Produto;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public abstract class CompraProdutoMapper {

	public static Document toDocument(CompraProduto compraProduto) {
		Document doc = new Document();
		doc.put(CompraProduto.Campos.quantidade.name(), compraProduto.getQuantidade());
		doc.put(CompraProduto.Campos.valorUnitario.name(), compraProduto.getValorUnitario());
		doc.put(CompraProduto.Campos.valorTotal.name(), compraProduto.getValorTotal());
		doc.put(CompraProduto.Campos.produto.name(), ProdutoMapper.toDocument(compraProduto.getProduto()));
		return doc;
	}

	public static CompraProduto toEntity(Document document) {
		Decimal128 quantidade = document.get(CompraProduto.Campos.quantidade.name(), Decimal128.class);
		Decimal128 valorUnitario = document.get(CompraProduto.Campos.valorUnitario.name(), Decimal128.class);
		Decimal128 valorTotal = document.get(CompraProduto.Campos.valorTotal.name(), Decimal128.class);

		Document docProduto = document.get(CompraProduto.Campos.produto.name(), Document.class);
		Produto produto = ProdutoMapper.toEntity(docProduto);

		CompraProduto compraProduto = new CompraProduto();
		compraProduto.setQuantidade(quantidade.bigDecimalValue());
		compraProduto.setValorUnitario(valorUnitario.bigDecimalValue());
		compraProduto.setValorTotal(valorTotal.bigDecimalValue());
		compraProduto.setProduto(produto);
		return compraProduto;
	}
}