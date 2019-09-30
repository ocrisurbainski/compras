package br.com.urbainski.backend.mapper;

import org.bson.Document;

import br.com.urbainski.backend.entity.Endereco;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public class EnderecoMapper {

	public static Document toDocument(Endereco endereco) {
		Document doc = new Document();
		doc.put(Endereco.Campos.cep.name(), endereco.getCep());
		doc.put(Endereco.Campos.logradouro.name(), endereco.getLogradouro());
		doc.put(Endereco.Campos.bairro.name(), endereco.getBairro());
		doc.put(Endereco.Campos.numero.name(), endereco.getNumero());
		doc.put(Endereco.Campos.municipio.name(), endereco.getMunicipio());
		doc.put(Endereco.Campos.siglaEstado.name(), endereco.getSiglaEstado());
		doc.put(Endereco.Campos.complemento.name(), endereco.getComplemento());
		return doc;
	}

	public static Endereco toEntity(Document document) {
		Endereco endereco = new Endereco();
		endereco.setCep(document.getString(Endereco.Campos.cep.name()));
		endereco.setLogradouro(document.getString(Endereco.Campos.logradouro.name()));
		endereco.setBairro(document.getString(Endereco.Campos.bairro.name()));
		endereco.setNumero(document.getString(Endereco.Campos.numero.name()));
		endereco.setMunicipio(document.getString(Endereco.Campos.municipio.name()));
		endereco.setSiglaEstado(document.getString(Endereco.Campos.siglaEstado.name()));
		endereco.setComplemento(document.getString(Endereco.Campos.complemento.name()));
		return endereco;
	}

}