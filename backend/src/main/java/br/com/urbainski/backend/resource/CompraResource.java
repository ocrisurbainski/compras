package br.com.urbainski.backend.resource;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.urbainski.backend.entity.Compra;
import br.com.urbainski.backend.pagination.PageRequest;
import br.com.urbainski.backend.pagination.PageResponse;
import br.com.urbainski.backend.rabbitmq.publisher.CompraPublisher;
import br.com.urbainski.backend.service.CompraService;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
@Path("/compras")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompraResource {
	
	@Inject CompraService compraService;
	
	@Inject CompraPublisher compraPublisher;
	
	@POST
	public Response save(@Valid Compra compra) {
		compraService.save(compra);
		compraPublisher.publish(compra);
		return Response.status(Status.CREATED).entity(compra).build();
	}
	
	@GET
    public Response findAll(@BeanParam PageRequest page) {
    	PageResponse<Compra> listCompras = compraService.findAll(page);
        return Response.ok(listCompras).build();
    }

}