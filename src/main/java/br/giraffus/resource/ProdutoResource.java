package br.giraffus.resource;

import java.util.List;

import br.giraffus.dto.ProdutoDTO;
import br.giraffus.service.ProdutoService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

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

    @GET
    @Path("/admin/{id}")
    @PermitAll
    public Response getIdAdmin(@PathParam("id") Long id) {
        return service.getIdAdmin(id);
    }

    @POST
    @PermitAll
    public Response insert(ProdutoDTO produtoDTO) {
        return service.insert(produtoDTO);
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ProdutoDTO dto) {
        return service.update(id, dto);
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
