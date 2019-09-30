package br.com.urbainski.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.Decimal128;

import br.com.urbainski.backend.entity.Venda;
import br.com.urbainski.backend.entity.VendaProduto;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public abstract class VendaMapper {

	public static Document toDocument(Venda venda) {

		List<Document> vendaProdutosDocs = new ArrayList<Document>(venda.getProdutos().size());
		for (VendaProduto vendaProduto : venda.getProdutos()) {
			vendaProdutosDocs.add(VendaProdutoMapper.toDocument(vendaProduto));
		}

		Document doc = new Document();
		doc.put(Venda.Campos._id.name(), venda.getId());
		doc.put(Venda.Campos.quantidadeTotal.name(), venda.getQuantidadeTotal());
		doc.put(Venda.Campos.valorTotal.name(), venda.getValorTotal());
		doc.put(Venda.Campos.situacaoEntrega.name(), venda.getSituacaoEntrega().name());
		doc.put(Venda.Campos.cliente.name(), VendaClienteMapper.toDocument(venda.getCliente()));
		doc.put(Venda.Campos.enderecoEntrega.name(), EnderecoMapper.toDocument(venda.getEnderecoEntrega()));
		doc.put(Venda.Campos.produtos.name(), vendaProdutosDocs);
		return doc;
	}

	@SuppressWarnings("unchecked")
	public static Venda toEntity(Document document) {

		Decimal128 valorTotal = document.get(Venda.Campos.valorTotal.name(), Decimal128.class);
		Decimal128 quantidadeTotal = document.get(Venda.Campos.quantidadeTotal.name(), Decimal128.class);

		Document docCliente = document.get(Venda.Campos.cliente.name(), Document.class);
		Document docEnderecoEntrega = document.get(Venda.Campos.enderecoEntrega.name(), Document.class);

		List<VendaProduto> produtos = new ArrayList<VendaProduto>();
		List<Document> vendaProdutosDocs = (List<Document>) document.get(Venda.Campos.produtos.name());
		for (Document vendaProdutoDoc : vendaProdutosDocs) {
			produtos.add(VendaProdutoMapper.toEntity(vendaProdutoDoc));
		}

		Venda venda = new Venda();
		venda.setId(document.getString(Venda.Campos._id.name()));
		venda.setQuantidadeTotal(quantidadeTotal.bigDecimalValue());
		venda.setValorTotal(valorTotal.bigDecimalValue());
		venda.setCliente(VendaClienteMapper.toEntity(docCliente));
		venda.setEnderecoEntrega(EnderecoMapper.toEntity(docEnderecoEntrega));
		return venda;
	}

}