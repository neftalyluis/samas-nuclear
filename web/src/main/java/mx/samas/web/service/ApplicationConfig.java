/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service;

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
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(mx.samas.web.service.Authentication.class);
        resources.add(mx.samas.web.service.PoweredByResponseFilter.class);
        resources.add(mx.samas.web.service.entries.StrategyService.class);
        resources.add(mx.samas.web.service.entries.TestingService.class);
        resources.add(mx.samas.web.service.entries.TransactionService.class);
        resources.add(mx.samas.web.service.entries.UserService.class);
        resources.add(mx.samas.web.service.errorhandling.AppExceptionMapper.class);
    }

}
