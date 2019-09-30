package br.com.urbainski.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.Decimal128;

import br.com.urbainski.backend.entity.Compra;
import br.com.urbainski.backend.entity.CompraProduto;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public abstract class CompraMapper {

	public static Document toDocument(Compra compra) {

		List<Document> vendaProdutosDocs = new ArrayList<Document>(compra.getProdutos().size());
		for (CompraProduto vendaProduto : compra.getProdutos()) {
			vendaProdutosDocs.add(CompraProdutoMapper.toDocument(vendaProduto));
		}

		Document doc = new Document();
		doc.put(Compra.Campos._id.name(), compra.getId());
		doc.put(Compra.Campos.quantidadeTotal.name(), compra.getQuantidadeTotal());
		doc.put(Compra.Campos.valorTotal.name(), compra.getValorTotal());
		doc.put(Compra.Campos.situacaoEntrega.name(), compra.getSituacaoEntrega().name());
		doc.put(Compra.Campos.cliente.name(), CompraClienteMapper.toDocument(compra.getCliente()));
		doc.put(Compra.Campos.enderecoEntrega.name(), EnderecoMapper.toDocument(compra.getEnderecoEntrega()));
		doc.put(Compra.Campos.produtos.name(), vendaProdutosDocs);
		return doc;
	}

	@SuppressWarnings("unchecked")
	public static Compra toEntity(Document document) {

		Decimal128 valorTotal = document.get(Compra.Campos.valorTotal.name(), Decimal128.class);
		Decimal128 quantidadeTotal = document.get(Compra.Campos.quantidadeTotal.name(), Decimal128.class);

		Document docCliente = document.get(Compra.Campos.cliente.name(), Document.class);
		Document docEnderecoEntrega = document.get(Compra.Campos.enderecoEntrega.name(), Document.class);

		List<CompraProduto> produtos = new ArrayList<CompraProduto>();
		List<Document> vendaProdutosDocs = (List<Document>) document.get(Compra.Campos.produtos.name());
		for (Document vendaProdutoDoc : vendaProdutosDocs) {
			produtos.add(CompraProdutoMapper.toEntity(vendaProdutoDoc));
		}

		Compra compra = new Compra();
		compra.setId(document.getString(Compra.Campos._id.name()));
		compra.setQuantidadeTotal(quantidadeTotal.bigDecimalValue());
		compra.setValorTotal(valorTotal.bigDecimalValue());
		compra.setCliente(CompraClienteMapper.toEntity(docCliente));
		compra.setEnderecoEntrega(EnderecoMapper.toEntity(docEnderecoEntrega));
		return compra;
	}

}