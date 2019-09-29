package br.com.urbainski.backend.codec.provider;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import br.com.urbainski.backend.codec.ClienteCodec;
import br.com.urbainski.backend.entity.Cliente;

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
		}
		return null;
	}

}