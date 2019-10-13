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

import br.com.urbainski.backend.entity.Produto;
import br.com.urbainski.backend.pagination.PageRequest;
import br.com.urbainski.backend.pagination.PageResponse;
import br.com.urbainski.backend.service.ProdutoService;

/**
 * 
 * @author Cristian Urbainski
 * @since 28/09/2019
 *
 */
@Path("/api/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {

	@Inject ProdutoService produtoService;

    @POST
    public Response save(@Valid Produto produto) {
    	produto = produtoService.save(produto);
    	return Response.status(Status.CREATED).entity(produto).build();
    }
    
    @PUT
    public Response update(@Valid Produto produto) {
    	produtoService.update(produto);
    	return Response.ok(produto).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
    	produtoService.delete(id);
    	return Response.noContent().build();
    }
    
    @GET
    @Path("/{id}")
    public Response findOne(@PathParam("id") String id) {
    	Produto produto = produtoService.findOne(id);
    	if (produto == null) {
    		throw new NotFoundException();
    	}
    	return Response.ok(produto).build();
    }
    
    @GET
    public Response findAll(@BeanParam PageRequest page) {
    	PageResponse<Produto> listProdutos = produtoService.findAll(page);
        return Response.ok(listProdutos).build();
    }
    
}