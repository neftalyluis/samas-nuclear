/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service.entries;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.beans.logic.StrategyBean;
import mx.samas.ejb.entities.Bond;
import mx.samas.ejb.entities.Equity;
import mx.samas.ejb.entities.SliceVector;
import mx.samas.ejb.entities.Strategy;

/**
 *
 * @author neftaly
 */
@Path("/strategy")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StrategyService {

    @EJB
    private StrategyBean sb;

    @GET
    public Response getAllStrategies() {
        List<Strategy> ls = sb.getAllStrategies();
        GenericEntity<List<Strategy>> list = new GenericEntity<List<Strategy>>(ls) {
        };
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getStrategyByID(@PathParam("id") long id) throws AppException {
        Strategy s = sb.getStrategyByID(id);
        return Response.ok(s).build();
    }

    @POST
    public Response createStrategy(Strategy s) {
        if (sb.persistStrategy(s)) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}/slice")
    public Response getSliceVectorFromID(@PathParam("id") long id) throws AppException {
        List<SliceVector> lsv = sb.getSlicesFromID(id);
        GenericEntity<List<SliceVector>> list = new GenericEntity<List<SliceVector>>(lsv) {
        };
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}/slice/active")
    public Response getActiveSliceVectorFromID(@PathParam("id") long id) throws AppException {
        List<SliceVector> lsv = sb.getActiveSlicesFromStrategy(id);
        for(SliceVector sv : lsv ){
            if (sv.getAsset() instanceof Bond){
                System.out.println(sv.getAsset().getTicker());
                Bond e = (Bond) sv.getAsset();
                System.out.println(e.getMaturityDate());
                sv.setAsset(e);
            }
        }
        GenericEntity<List<SliceVector>> list = new GenericEntity<List<SliceVector>>(lsv) {
        };
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}/slice/{sliceID}/")
    public Response getSliceFromStrategy(@PathParam("id") long id, @PathParam("sliceID") long sliceId) throws AppException {
        SliceVector sv = sb.getSliceFromStrategyAndId(id, sliceId);
        return Response.ok(sv).build();
    }

    @PUT
    @Path("/{id}/slice/")
    public Response updateSlices(@PathParam("id") long id, List<SliceVector> lsv) throws AppException {
        
        for(SliceVector sv : lsv){
            System.out.println(sv.getAsset().getName());
        }
        Strategy s = sb.getStrategyByID(id);
        s.setSlices(lsv);
        
        sb.updateStrategy(id, lsv);
        return Response.ok().build();
    }

}
