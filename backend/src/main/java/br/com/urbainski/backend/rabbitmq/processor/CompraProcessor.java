package br.com.urbainski.backend.rabbitmq.processor;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import br.com.urbainski.backend.entity.enums.SituacaoEntrega;
import br.com.urbainski.backend.rabbitmq.connection.RabbitmqConnection;
import br.com.urbainski.backend.rabbitmq.publisher.CompraPublisher;
import br.com.urbainski.backend.rabbitmq.util.RabbitmqUtil;
import br.com.urbainski.backend.service.CompraService;

/**
 * 
 * @author Cristian Urbainski
 * @since 30/09/2019
 *
 */
@Singleton
public class CompraProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(CompraPublisher.class);

	@Inject
	RabbitmqConnection rabbitmqConnection;
	
	@Inject
	CompraService compraService;

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
	
	public void startsConsumer() {
		if (this.channel == null) {
			LOG.error("Channel não foi criado.");
			return;
		}
		try {
			this.channel.basicConsume(RabbitmqUtil.COMPRAS_QUEUE, false, new ComprasConsumer(this.channel, compraService));
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @author Cristian Urbainski
	 * @since 01/10/2019
	 *
	 */
	private static class ComprasConsumer extends DefaultConsumer {
		
		CompraService compraService;
	
		public ComprasConsumer(Channel channel, CompraService compraService) {
			super(channel);
			this.compraService = compraService;
		}
		
		@Override
		public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
				throws IOException {
			String id = new String(body, "UTF-8");
			compraService.updateSituacaoEntrega(id, SituacaoEntrega.ENVIADA);
			
			long deliveryTag = envelope.getDeliveryTag();
			getChannel().basicAck(deliveryTag, false);
		}
	}
	
}