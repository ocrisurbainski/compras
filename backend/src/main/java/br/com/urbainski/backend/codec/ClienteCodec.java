package br.com.urbainski.backend.codec;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

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
import br.com.urbainski.backend.mapper.ClienteMapper;

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
	public void encode(BsonWriter writer, Cliente cliente, EncoderContext encoderContext) {
		Document doc = ClienteMapper.toDocument(cliente);
		documentCodec.encode(writer, doc, encoderContext);
	}

	@Override
	public Class<Cliente> getEncoderClass() {
		return Cliente.class;
	}

	@Override
	public Cliente decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		return ClienteMapper.toEntity(document);
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