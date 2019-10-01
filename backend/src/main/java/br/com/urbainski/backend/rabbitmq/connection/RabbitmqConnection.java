package br.com.urbainski.backend.rabbitmq.connection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author Cristian Urbainski
 * @since 30/09/2019
 *
 */
@Singleton
public class RabbitmqConnection {

	private static final Logger LOG = LoggerFactory.getLogger(RabbitmqConnection.class);

	@ConfigProperty(name = "rabbitmq.host")
	String host;

	@ConfigProperty(name = "rabbitmq.port")
	Integer port;

	@ConfigProperty(name = "rabbitmq.username")
	String username;

	@ConfigProperty(name = "rabbitmq.password")
	String password;

	private Connection connection;

	@PostConstruct
	public void init() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setPort(port);
		factory.setUsername(username);
		factory.setPassword(password);
		try {
			this.connection = factory.newConnection();
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@PreDestroy
	public void destroy() {
		if (this.connection == null) {
			return;
		}
		try {
			this.connection.close();
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public Connection getConnection() {
		return connection;
	}

}