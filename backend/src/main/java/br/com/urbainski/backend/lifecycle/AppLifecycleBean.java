package br.com.urbainski.backend.lifecycle;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.urbainski.backend.rabbitmq.processor.CompraProcessor;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

/**
 * 
 * @author Cristian Urbainski
 * @since 30/09/2019
 *
 */
@ApplicationScoped
public class AppLifecycleBean {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppLifecycleBean.class);
	
	@Inject
	CompraProcessor compraProcessor;
	
	public void onStart(@Observes StartupEvent event) {
		LOG.info("Starts project...");
		compraProcessor.startsConsumer();
	}
	
	public void onStop(@Observes ShutdownEvent event) {
		LOG.info("Stoping project...");
	}
	
}