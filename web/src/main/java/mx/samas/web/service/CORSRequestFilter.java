/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author neftaly
 */
@Provider
@PreMatching
public class CORSRequestFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(CORSRequestFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // When HttpMethod comes as OPTIONS, just acknowledge that it accepts...
        if (requestContext.getRequest().getMethod().equals("OPTIONS")) {
            LOG.info("HTTP Method (OPTIONS) - Detected!");

            // Just send a OK signal back to the browser
            requestContext.abortWith(Response.status(Response.Status.OK).build());
        }
    }

}
