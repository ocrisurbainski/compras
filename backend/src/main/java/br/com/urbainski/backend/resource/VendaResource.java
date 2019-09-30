package br.com.urbainski.backend.resource;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.urbainski.backend.entity.Venda;
import br.com.urbainski.backend.service.VendaService;

/**
 * 
 * @author Cristian Urbainski
 * @since 29/09/2019
 *
 */
@Path("/vendas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VendaResource {
	
	@Inject VendaService vendaService;
	
	@POST
	public Response save(@Valid Venda venda) {
		vendaService.save(venda);
		return Response.status(Status.CREATED).entity(venda).build();
	}

}