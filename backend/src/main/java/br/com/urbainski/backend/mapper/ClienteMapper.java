package br.com.urbainski.backend.mapper;

import org.bson.Document;

import br.com.urbainski.backend.entity.Cliente;
import br.com.urbainski.backend.entity.enums.TipoPessoa;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public abstract class ClienteMapper {

	public static Document toDocument(Cliente cliente) {
		Document doc = new Document();
		doc.put(Cliente.Campos._id.name(), cliente.getId());
		doc.put(Cliente.Campos.nome.name(), cliente.getNome());
		doc.put(Cliente.Campos.email.name(), cliente.getEmail());
		doc.put(Cliente.Campos.tipoPessoa.name(), cliente.getTipoPessoa().name());
		doc.put(Cliente.Campos.numeroDocumento.name(), cliente.getNumeroDocumento());
		doc.put(Cliente.Campos.endereco.name(), EnderecoMapper.toDocument(cliente.getEndereco()));
		return doc;
	}

	public static Cliente toEntity(Document document) {
		Cliente cliente = new Cliente();
		cliente.setId(document.getString(Cliente.Campos._id.name()));
		cliente.setNome(document.getString(Cliente.Campos.nome.name()));
		cliente.setEmail(document.getString(Cliente.Campos.email.name()));
		cliente.setTipoPessoa(TipoPessoa.valueOf(document.getString(Cliente.Campos.tipoPessoa.name())));
		cliente.setNumeroDocumento(document.getString(Cliente.Campos.numeroDocumento.name()));

		Document documentEndereco = document.get(Cliente.Campos.endereco.name(), Document.class);
		cliente.setEndereco(EnderecoMapper.toEntity(documentEndereco));

		return cliente;
	}

}