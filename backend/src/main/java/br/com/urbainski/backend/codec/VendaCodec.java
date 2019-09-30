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

import br.com.urbainski.backend.entity.Venda;
import br.com.urbainski.backend.mapper.VendaMapper;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public class VendaCodec implements CollectibleCodec<Venda> {

	private final Codec<Document> documentCodec;

	public VendaCodec() {
		this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
	}

	@Override
	public void encode(BsonWriter writer, Venda venda, EncoderContext encoderContext) {
		Document doc = VendaMapper.toDocument(venda);
		documentCodec.encode(writer, doc, encoderContext);
	}

	@Override
	public Class<Venda> getEncoderClass() {
		return Venda.class;
	}

	@Override
	public Venda decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		return VendaMapper.toEntity(document);
	}

	@Override
	public Venda generateIdIfAbsentFromDocument(Venda venda) {
		if (!documentHasId(venda)) {
			venda.setId(UUID.randomUUID().toString());
		}
		return venda;
	}

	@Override
	public boolean documentHasId(Venda venda) {
		return isNotEmpty(venda.getId());
	}

	@Override
	public BsonValue getDocumentId(Venda venda) {
		return new BsonString(venda.getId());
	}
	
}