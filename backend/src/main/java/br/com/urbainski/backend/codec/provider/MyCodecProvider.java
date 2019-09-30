package br.com.urbainski.backend.codec.provider;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import br.com.urbainski.backend.codec.ClienteCodec;
import br.com.urbainski.backend.codec.ProdutoCodec;
import br.com.urbainski.backend.codec.VendaCodec;
import br.com.urbainski.backend.entity.Cliente;
import br.com.urbainski.backend.entity.Produto;
import br.com.urbainski.backend.entity.Venda;

/**
 * 
 * @author Cristian Urbainski
 * @since 27/09/2019
 *
 */
public class MyCodecProvider implements CodecProvider {

	@SuppressWarnings("unchecked")
	@Override
	public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
		if (clazz == Cliente.class) {
			return (Codec<T>) new ClienteCodec();
		} else if (clazz == Produto.class) {
			return (Codec<T>) new ProdutoCodec();
		} else if (clazz == Venda.class) {
			return (Codec<T>) new VendaCodec();
		}
		return null;
	}

}