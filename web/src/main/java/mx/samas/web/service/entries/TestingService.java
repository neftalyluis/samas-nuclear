/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service.entries;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.samas.ejb.beans.logic.TestSuiteBean;
import mx.samas.ejb.entities.Test;
import mx.samas.web.service.dto.TestEntryDTO;
import mx.samas.web.service.dto.TestTransactionDTO;

/**
 *
 * @author neftaly
 */
@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestingService {


    @EJB
    private TestSuiteBean tsb;

    /**
     *
     * @param te El registro de los contratos a probar
     * @return Si se pudo registrar correctamente la prueba
     */
    @POST
    public Response newTest(TestEntryDTO te) {
        Test t = new Test();
        return Response.ok().build();
    }

    /**
     *
     * @return Las lista completa de unidades de prueba disponibles
     */
    @GET
    public Response getTests() {
        return Response.ok().build();
    }

    /**
     *
     * @param id La ID de la Unidad de Prueba
     * @return Descripcion de una unidad de prueba
     */
    @Path("{id}")
    @GET
    public Response getTestByID(@PathParam("id") int id) {
        return Response.ok().build();
    }

    /**
     *
     * @param tt Una lista de operaciones a realizar para cierta unidad de
     * prueba
     * @param id La ID de la Unidad de Prueba
     * @return
     */
    @Path("{id}/operations")
    @POST
    public Response addOperations(TestTransactionDTO tt, @PathParam("id") int id) {
        return Response.ok().build();
    }

    /**
     * Se somete a hacer un proceso de cierre en el dia indicado
     *
     * @param id El ID de la unidad de Prueba
     * @return Un reporte
     */
    @Path("{id}/close/")
    @GET
    public Response closeDay(@PathParam("id") int id) {
        return Response.ok().build();
    }

}
