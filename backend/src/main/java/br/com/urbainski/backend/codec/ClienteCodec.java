package br.com.urbainski.backend.codec;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.join;

import java.util.UUID;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import com.mongodb.MongoClient;

import br.com.urbainski.backend.entity.Cliente;
import br.com.urbainski.backend.entity.Endereco;
import br.com.urbainski.backend.entity.enums.TipoPessoa;

/**
 * 
 * @author Cristian Urbainski
 * @since 27/09/2019
 *
 */
public class ClienteCodec implements CollectibleCodec<Cliente> {

	private final Codec<Document> documentCodec;

	public ClienteCodec() {
		this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
	}

	@Override
	public void encode(BsonWriter writer, Cliente value, EncoderContext encoderContext) {
		Document doc = new Document();
		doc.put(Cliente.Campos._id.name(), value.getId());
		doc.put(Cliente.Campos.nome.name(), value.getNome());
		doc.put(Cliente.Campos.email.name(), value.getEmail());
		doc.put(Cliente.Campos.tipoPessoa.name(), value.getTipoPessoa().name());
		doc.put(Cliente.Campos.numeroDocumento.name(), value.getNumeroDocumento());
		doc.put(join(Cliente.Campos.endereco.name(), ".", Endereco.Campos.cep.name()), value.getEndereco().getCep());
		doc.put(join(Cliente.Campos.endereco.name(), ".", Endereco.Campos.logradouro.name()),
				value.getEndereco().getLogradouro());
		doc.put(join(Cliente.Campos.endereco.name(), ".", Endereco.Campos.bairro.name()),
				value.getEndereco().getBairro());
		doc.put(join(Cliente.Campos.endereco.name(), ".", Endereco.Campos.numero.name()),
				value.getEndereco().getNumero());
		doc.put(join(Cliente.Campos.endereco.name(), ".", Endereco.Campos.municipio.name()),
				value.getEndereco().getMunicipio());
		doc.put(join(Cliente.Campos.endereco.name(), ".", Endereco.Campos.siglaEstado.name()),
				value.getEndereco().getSiglaEstado());
		doc.put(join(Cliente.Campos.endereco.name(), ".", Endereco.Campos.complemento.name()),
				value.getEndereco().getComplemento());
		documentCodec.encode(writer, doc, encoderContext);
	}

	@Override
	public Class<Cliente> getEncoderClass() {
		return Cliente.class;
	}

	@Override
	public Cliente decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		Document documentEndereco = document.get(Cliente.Campos.endereco.name(), Document.class);

		Endereco endereco = new Endereco();
		endereco.setCep(documentEndereco.getString(Endereco.Campos.cep.name()));
		endereco.setLogradouro(documentEndereco.getString(Endereco.Campos.logradouro.name()));
		endereco.setBairro(documentEndereco.getString(Endereco.Campos.bairro.name()));
		endereco.setNumero(documentEndereco.getString(Endereco.Campos.numero.name()));
		endereco.setMunicipio(documentEndereco.getString(Endereco.Campos.municipio.name()));
		endereco.setSiglaEstado(documentEndereco.getString(Endereco.Campos.siglaEstado.name()));
		endereco.setComplemento(documentEndereco.getString(Endereco.Campos.complemento.name()));

		Cliente cliente = new Cliente();
		cliente.setId(document.getString(Cliente.Campos._id.name()));
		cliente.setNome(document.getString(Cliente.Campos.nome.name()));
		cliente.setEmail(document.getString(Cliente.Campos.email.name()));
		cliente.setTipoPessoa(TipoPessoa.valueOf(document.getString(Cliente.Campos.tipoPessoa.name())));
		cliente.setNumeroDocumento(document.getString(Cliente.Campos.numeroDocumento.name()));
		cliente.setEndereco(endereco);

		return cliente;
	}

	@Override
	public Cliente generateIdIfAbsentFromDocument(Cliente document) {
		if (!documentHasId(document)) {
			document.setId(UUID.randomUUID().toString());
		}
		return document;
	}

	@Override
	public boolean documentHasId(Cliente document) {
		return isNotEmpty(document.getId());
	}

	@Override
	public BsonValue getDocumentId(Cliente document) {
		return new BsonString(document.getId());
	}

}