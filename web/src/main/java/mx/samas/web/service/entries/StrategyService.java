/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service.entries;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.samas.ejb.beans.logic.StrategyBean;
import mx.samas.ejb.entities.Strategy;
import mx.samas.ejb.beans.exceptions.NotACompleteStrategyException;

/**
 *
 * @author neftaly
 */
@Stateless
@Path("strategy")
@Api(value = "Estrategias")
public class StrategyService {

    @EJB
    private StrategyBean sb;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Crear nueva estrategia")
    public Response create(Strategy entity) {
        if (sb.persistStrategy(entity)) {
            return Response.accepted().build();
        } else {
            throw new NotACompleteStrategyException();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Editar estrategia")
    public void edit(@PathParam("id") Long id, Strategy entity) {
    }

    @DELETE
    @Path("{id}")
    @ApiOperation(value = "Eliminar estrategia")
    public void remove(@PathParam("id") Long id) {
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Buscar estrategia por ID")
    public Strategy find(@PathParam("id") Long id) {
        return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Obtener todas las estrategias existentes")
    public List<Strategy> findAll() {
        return null;
    }

}
