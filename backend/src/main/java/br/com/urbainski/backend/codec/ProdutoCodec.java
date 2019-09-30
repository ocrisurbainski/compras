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

import br.com.urbainski.backend.entity.Produto;
import br.com.urbainski.backend.mapper.ProdutoMapper;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
public class ProdutoCodec implements CollectibleCodec<Produto> {

	private final Codec<Document> documentCodec;

	public ProdutoCodec() {
		this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
	}

	@Override
	public void encode(BsonWriter writer, Produto produto, EncoderContext encoderContext) {
		Document doc = ProdutoMapper.toDocument(produto);
		documentCodec.encode(writer, doc, encoderContext);
	}

	@Override
	public Class<Produto> getEncoderClass() {
		return Produto.class;
	}

	@Override
	public Produto decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);
		return ProdutoMapper.toEntity(document);
	}

	@Override
	public Produto generateIdIfAbsentFromDocument(Produto produto) {
		if (!documentHasId(produto)) {
			produto.setId(UUID.randomUUID().toString());
		}
		return produto;
	}

	@Override
	public boolean documentHasId(Produto produto) {
		return isNotEmpty(produto.getId());
	}

	@Override
	public BsonValue getDocumentId(Produto produto) {
		return new BsonString(produto.getId());
	}

}