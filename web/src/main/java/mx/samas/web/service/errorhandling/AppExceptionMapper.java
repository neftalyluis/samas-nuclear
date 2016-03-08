/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service.errorhandling;

import mx.samas.ejb.beans.exceptions.AppException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author neftaly
 */
@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {
    
    @Override
    public Response toResponse(AppException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(new ErrorMessage(exception)).build();
    }
    
}
