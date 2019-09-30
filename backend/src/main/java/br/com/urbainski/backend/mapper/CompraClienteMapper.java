package br.com.urbainski.backend.mapper;

import org.bson.Document;

import br.com.urbainski.backend.entity.CompraCliente;
import br.com.urbainski.backend.entity.enums.TipoPessoa;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public abstract class CompraClienteMapper {

	public static Document toDocument(CompraCliente clienteVenda) {
		Document doc = new Document();
		doc.put(CompraCliente.Campos._id.name(), clienteVenda.getId());
		doc.put(CompraCliente.Campos.nome.name(), clienteVenda.getNome());
		doc.put(CompraCliente.Campos.email.name(), clienteVenda.getEmail());
		doc.put(CompraCliente.Campos.tipoPessoa.name(), clienteVenda.getTipoPessoa().name());
		doc.put(CompraCliente.Campos.numeroDocumento.name(), clienteVenda.getNumeroDocumento());
		return doc;
	}

	public static CompraCliente toEntity(Document document) {
		CompraCliente clienteVenda = new CompraCliente();
		clienteVenda.setId(document.getString(CompraCliente.Campos._id.name()));
		clienteVenda.setNome(document.getString(CompraCliente.Campos.nome.name()));
		clienteVenda.setEmail(document.getString(CompraCliente.Campos.email.name()));
		clienteVenda.setTipoPessoa(TipoPessoa.valueOf(document.getString(CompraCliente.Campos.tipoPessoa.name())));
		clienteVenda.setNumeroDocumento(document.getString(CompraCliente.Campos.numeroDocumento.name()));
		return clienteVenda;
	}

}