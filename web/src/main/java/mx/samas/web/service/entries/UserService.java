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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.samas.ejb.beans.logic.UserBean;
import mx.samas.ejb.entities.User;

/**
 *
 * @author neftaly
 */

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {
    
    @EJB
    UserBean ub;
    
    @GET
    public Response getAllUsers(){
        List<User> lu = ub.getAllUsers();
        GenericEntity<List<User>> list = new GenericEntity<List<User>>(lu){};
        return Response.ok(list).build();
        
    }
    
}
