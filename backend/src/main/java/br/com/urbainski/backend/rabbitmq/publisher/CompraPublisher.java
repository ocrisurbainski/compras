package br.com.urbainski.backend.rabbitmq.publisher;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;

import br.com.urbainski.backend.entity.Compra;
import br.com.urbainski.backend.rabbitmq.connection.RabbitmqConnection;
import br.com.urbainski.backend.rabbitmq.util.RabbitmqUtil;

/**
 * 
 * @author Cristian Urbainski
 * @since 30/09/2019
 *
 */
@Singleton
public class CompraPublisher {

	private static final Logger LOG = LoggerFactory.getLogger(CompraPublisher.class);

	@Inject
	RabbitmqConnection rabbitmqConnection;

	private Channel channel;

	@PostConstruct
	public void init() {
		try {
			if (rabbitmqConnection.getConnection() == null) {
				return;
			}
			this.channel = rabbitmqConnection.getConnection().createChannel();
			this.channel.queueDeclare(RabbitmqUtil.COMPRAS_QUEUE, true, false, false, null);
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@PreDestroy
	public void destroy() {
		if (this.channel == null) {
			return;
		}
		try {
			this.channel.close();
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void publish(Compra compra) {
		if (compra == null) {
			throw new IllegalArgumentException("Compra deve ser informado.");
		}
		if (StringUtils.isBlank(compra.getId())) {
			throw new IllegalArgumentException("Identificador da compra deve estar informado.");
		}
		if (this.channel == null) {
			LOG.error("Channel não foi criado.");
			return;
		}
		try {
			this.channel.basicPublish("", RabbitmqUtil.COMPRAS_QUEUE, null, compra.getId().getBytes());
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		}
	}

}