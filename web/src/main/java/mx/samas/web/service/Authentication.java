/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.samas.ejb.entities.Client;
import mx.samas.ejb.entities.User;
import mx.samas.ejb.beans.exceptions.AppException;

/**
 *
 * @author neftaly
 *
 * Aqui comenzamos a hacer el modulo de autenticación
 */
@Path("/login")
public class Authentication {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(User u) {
        String user = u.getEmail();
        String password = u.getPassword();
        return Response.ok(u).build();
    }

    @POST
    @Path("/echo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Client echo(Client c) {
        return c;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void echo() throws AppException {
        throw new AppException();
    }

}
//
//    @ApiOperation(value = "Finds Pets by status",
//            notes = "Multiple status values can be provided with comma seperated strings",
//            response = User.class,
//            responseContainer = "List")