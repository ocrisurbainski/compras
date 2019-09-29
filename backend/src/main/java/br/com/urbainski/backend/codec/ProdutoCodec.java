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
import org.bson.types.Decimal128;

import com.mongodb.MongoClient;

import br.com.urbainski.backend.entity.Produto;

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
	public void encode(BsonWriter writer, Produto value, EncoderContext encoderContext) {
		Document doc = new Document();
		doc.put(Produto.Campos._id.name(), value.getId());
		doc.put(Produto.Campos.nome.name(), value.getNome());
		doc.put(Produto.Campos.marca.name(), value.getMarca());
		doc.put(Produto.Campos.valorVenda.name(), value.getValorVenda());
		documentCodec.encode(writer, doc, encoderContext);
	}

	@Override
	public Class<Produto> getEncoderClass() {
		return Produto.class;
	}

	@Override
	public Produto decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);

		Produto produto = new Produto();
		produto.setId(document.getString(Produto.Campos._id.name()));
		produto.setNome(document.getString(Produto.Campos.nome.name()));
		produto.setMarca(document.getString(Produto.Campos.marca.name()));
		produto.setValorVenda(document.get(Produto.Campos.valorVenda.name(), Decimal128.class).bigDecimalValue());
		return produto;
	}

	@Override
	public Produto generateIdIfAbsentFromDocument(Produto document) {
		if (!documentHasId(document)) {
			document.setId(UUID.randomUUID().toString());
		}
		return document;
	}

	@Override
	public boolean documentHasId(Produto document) {
		return isNotEmpty(document.getId());
	}

	@Override
	public BsonValue getDocumentId(Produto document) {
		return new BsonString(document.getId());
	}

}