/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service.entries;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.samas.ejb.beans.logic.TransactionBean;

/**
 *
 * @author neftaly
 */
@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionService {
    
    @EJB
    private TransactionBean tb;

    /**
     *
     * @param id El ID de la Transaccion
     * @return El detalle de la Transaccion
     */
    @GET
    @Path("{id}")
    public Response getByID(@PathParam("id") int id) {
        return Response.ok().build();
    }

    /**
     *
     * @return Todas las transacciones disponibles
     */
    @GET
    public Response getAll() {
        return Response.ok().build();

    }

    /**
     *
     * @param owner El nombre del propietario
     * @return Todas las transacciones que pertenecen a ese propietario
     */
    @GET
    @Path("/owner/{name}")
    public Response getByOwner(@PathParam("name") String owner) {
        return Response.ok().build();

    }

}
