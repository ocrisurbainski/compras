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

import br.com.urbainski.backend.entity.Compra;
import br.com.urbainski.backend.mapper.CompraMapper;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public class CompraCodec implements CollectibleCodec<Compra> {

	private final Codec<Document> documentCodec;

	public CompraCodec() {
		this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
	}

	@Override
	public void encode(BsonWriter writer, Compra compra, EncoderContext encoderContext) {
		Document doc = CompraMapper.toDocument(compra);
		documentCodec.encode(writer, doc, encoderContext);
	}

	@Override
	public Class<Compra> getEncoderClass() {
		return Compra.class;
	}

	@Override
	public Compra decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		return CompraMapper.toEntity(document);
	}

	@Override
	public Compra generateIdIfAbsentFromDocument(Compra compra) {
		if (!documentHasId(compra)) {
			compra.setId(UUID.randomUUID().toString());
		}
		return compra;
	}

	@Override
	public boolean documentHasId(Compra compra) {
		return isNotEmpty(compra.getId());
	}

	@Override
	public BsonValue getDocumentId(Compra compra) {
		return new BsonString(compra.getId());
	}

}