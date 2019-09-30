package br.com.urbainski.backend.mapper;

import org.bson.Document;

import br.com.urbainski.backend.entity.VendaCliente;
import br.com.urbainski.backend.entity.enums.TipoPessoa;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public abstract class VendaClienteMapper {

	public static Document toDocument(VendaCliente clienteVenda) {
		Document doc = new Document();
		doc.put(VendaCliente.Campos._id.name(), clienteVenda.getId());
		doc.put(VendaCliente.Campos.nome.name(), clienteVenda.getNome());
		doc.put(VendaCliente.Campos.email.name(), clienteVenda.getEmail());
		doc.put(VendaCliente.Campos.tipoPessoa.name(), clienteVenda.getTipoPessoa().name());
		doc.put(VendaCliente.Campos.numeroDocumento.name(), clienteVenda.getNumeroDocumento());
		return doc;
	}

	public static VendaCliente toEntity(Document document) {
		VendaCliente clienteVenda = new VendaCliente();
		clienteVenda.setId(document.getString(VendaCliente.Campos._id.name()));
		clienteVenda.setNome(document.getString(VendaCliente.Campos.nome.name()));
		clienteVenda.setEmail(document.getString(VendaCliente.Campos.email.name()));
		clienteVenda.setTipoPessoa(TipoPessoa.valueOf(document.getString(VendaCliente.Campos.tipoPessoa.name())));
		clienteVenda.setNumeroDocumento(document.getString(VendaCliente.Campos.numeroDocumento.name()));
		return clienteVenda;
	}

}