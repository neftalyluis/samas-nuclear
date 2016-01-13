/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.rest;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author neftaly
 */
@Singleton
@Lock(LockType.READ)
@Path("/test")
public class TestEntry {

    @GET
    public String ejb() {
        return "Esta es una prueba con Jersey";
    }

}
