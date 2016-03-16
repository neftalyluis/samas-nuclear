/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service;

import io.swagger.annotations.Api;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.samas.ejb.beans.logic.ClientBean;
import mx.samas.ejb.entities.Client;

/**
 *
 * @author neftaly
 */
@Path("client")
@Api(value = "Clientes")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ClientService {

    @EJB
    private ClientBean cb;

    @POST
    public Response create(Client c) {
        return null;
    }

    @DELETE
    public Response remove(Client c) {
        return null;
    }

    @DELETE
    public Response removeByID(Integer id) {
        return null;
    }

    @PUT
    public Response edit(Client c) {
        return null;
    }

    @GET
    public Response getByID(Integer id) {
        return null;
    }

    @GET
    public Response getByName(String name) {
        return null;
    }
}
