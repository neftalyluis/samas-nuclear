/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service;

import io.swagger.jaxrs.config.BeanConfig;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author neftaly
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {

    public ApplicationConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("api");
        beanConfig.setResourcePackage("mx.samas.web.service");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(mx.samas.web.service.Authentication.class);
        resources.add(mx.samas.web.service.CORSFilter.class);
        resources.add(mx.samas.web.service.CORSRequestFilter.class);
        resources.add(mx.samas.web.service.entries.RiskProfileFacadeREST.class);
        resources.add(mx.samas.web.service.entries.StrategyService.class);
        resources.add(mx.samas.web.service.errorhandling.AppExceptionMapper.class);
    }

}
