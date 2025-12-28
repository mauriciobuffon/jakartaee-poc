package br.dev.webit.application.jaxrs;

import java.util.Collection;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.dev.webit.domain.Agregador;
import br.dev.webit.domain.AgregadorId;
import br.dev.webit.domain.AgregadorRepository;
import br.dev.webit.domain.ValorObjeto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("agregadores")
@Produces("application/json")
@RequestScoped
@Transactional
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Inject
    private AgregadorRepository repository;

    @Context
    UriInfo uriInfo;

    @GET
    public Collection<Agregador> getAll(
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("50") int limit) {
        LOGGER.trace("Route GET 'agregadores?offset={}&limit={}' called", offset, limit);
        return repository.findAll(offset, limit);
    }

    @POST
    public Response post() {
        LOGGER.trace("Route POST 'agregadores' called");
        Agregador agregador = new Agregador();
        repository.put(agregador);
        return Response
                .created(
                        uriInfo.getAbsolutePathBuilder().path("{id}").build(agregador.getId()))
                .entity(agregador)
                .build();
    }

    @Path("{id : \\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}}")
    @GET
    public Agregador get(@PathParam("id") UUID id) {
        LOGGER.trace("Route GET 'agregadores/{}' called", id);
        return repository.find(new AgregadorId(id)).orElseThrow(NotFoundException::new);
    }

    @Path("{id : \\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}}")
    @DELETE
    public void delete(@PathParam("id") UUID id) {
        LOGGER.trace("Route DELETE 'agregadores/{}' called", id);
        repository.remove(repository.find(new AgregadorId(id)).orElseThrow(NotFoundException::new));
    }

    @Path("{id : \\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}}/entidades")
    @POST
    public void postEntidade(@PathParam("id") UUID id, String dado) {
        LOGGER.trace("Route POST 'agregadores/{}/entidades' called with body {}", id, dado);
        Agregador agregador = repository.find(new AgregadorId(id)).orElseThrow(NotFoundException::new);
        agregador.addEntidade(new ValorObjeto(dado));
        repository.put(agregador);
    }

    @Path("{id : \\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}}/objetos")
    @POST
    public void postObjeto(@PathParam("id") UUID id, String dado) {
        LOGGER.trace("Route POST 'agregadores/{}/objetos' called with body {}", id, dado);
        Agregador agregador = repository.find(new AgregadorId(id)).orElseThrow(NotFoundException::new);
        agregador.addObjeto(new ValorObjeto(dado));
    }
}
