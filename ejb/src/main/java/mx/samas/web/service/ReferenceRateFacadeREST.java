/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mx.samas.ejb.entities.ReferenceRate;

/**
 *
 * @author neftaly
 */
@Stateless
@Path("mx.samas.ejb.entities.referencerate")
public class ReferenceRateFacadeREST extends AbstractFacade<ReferenceRate> {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    public ReferenceRateFacadeREST() {
        super(ReferenceRate.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(ReferenceRate entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, ReferenceRate entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ReferenceRate find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<ReferenceRate> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ReferenceRate> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
