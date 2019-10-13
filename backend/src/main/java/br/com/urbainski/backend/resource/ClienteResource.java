package br.com.urbainski.backend.resource;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.urbainski.backend.entity.Cliente;
import br.com.urbainski.backend.pagination.PageRequest;
import br.com.urbainski.backend.pagination.PageResponse;
import br.com.urbainski.backend.service.ClienteService;

/**
 * 
 * @author Cristian Urbainski
 * @since 28/09/2019
 *
 */
@Path("/api/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {
	
	@Inject ClienteService clienteService;

    @POST
    public Response save(@Valid Cliente cliente) {
    	cliente = clienteService.save(cliente);
    	return Response.status(Status.CREATED).entity(cliente).build();
    }
    
    @PUT
    public Response update(@Valid Cliente cliente) {
    	clienteService.update(cliente);
    	return Response.ok(cliente).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
    	clienteService.delete(id);
    	return Response.noContent().build();
    }
    
    @GET
    @Path("/{id}")
    public Response findOne(@PathParam("id") String id) {
    	Cliente cliente = clienteService.findOne(id);
    	if (cliente == null) {
    		throw new NotFoundException();
    	}
    	return Response.ok(cliente).build();
    }
    
    @GET
    public Response findAll(@BeanParam PageRequest page) {
    	PageResponse<Cliente> listClientes = clienteService.findAll(page);
        return Response.ok(listClientes).build();
    }
    
}