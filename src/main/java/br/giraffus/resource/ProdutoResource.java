package br.giraffus.resource;

import br.giraffus.dto.ProdutoDTO;
import br.giraffus.dto.ProdutoUpdateDTO;
import br.giraffus.dto.responseDTO.ProdutoResponseDTO;
import br.giraffus.service.ProdutoService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/produtos")
@Produces("application/json")
@Consumes("application/json")
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @GET
    @PermitAll
    @Path("/{page}/{pageSize}")
    public Response getAll(@PathParam("page") int page, @PathParam("pageSize") int pageSize) {
        return service.getAll(page, pageSize);
    }
    @GET
    @PermitAll
    @Path("/size")
    public Response getAllSize() {
        return service.getAllSize();
    }

    @GET
    @Path("/estoques")
    @PermitAll
    public Response getEstoques() {
        return service.estoque();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getId(@PathParam("id") Long id) {
        return service.getId(id);
    }

    @POST
    @PermitAll
    public Response insert(ProdutoDTO produtoDTO) {
        return service.insert(produtoDTO);
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ProdutoUpdateDTO produtoUpdateDTO) {
        return service.update(id, produtoUpdateDTO);
    }
    @PATCH
    @PermitAll
    @Path("/add/{id}")
    public Response addEstoque(@PathParam("id") Long id, Integer quantidade) {
        return service.addEstoque(id, quantidade);
    }
    @PATCH
    @RolesAllowed({"Admin"})
    @Path("/remove/{id}")
    public Response removeEstoque(@PathParam("id") Long id, Integer quantidade) {
        return service.removeEstoque(id, quantidade);
    }

    @POST
    @PermitAll
    @Path("/addcategorias/{id}")
    public Response addCategorias(@PathParam("id") Long id, List<Long> categorias) {
        return service.addCategoria(id, categorias);
    }

    @PATCH
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
