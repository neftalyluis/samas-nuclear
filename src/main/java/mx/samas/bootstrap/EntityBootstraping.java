/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.bootstrap;

import java.util.logging.Level;
import java.util.logging.Logger;
import mx.samas.domain.Cliente;
import mx.samas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Component
public class EntityBootstraping implements ApplicationListener<ApplicationReadyEvent> {
    
    private static final Logger LOG = Logger.getLogger(EntityBootstraping.class.getName());
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        
        System.out.println("========================================");
        System.out.println("  / ____|  /\\   |  \\/  |   /\\    / ____|\n"
                + " | (___   /  \\  | \\  / |  /  \\  | (___  \n"
                + "  \\___ \\ / /\\ \\ | |\\/| | / /\\ \\  \\___ \\ \n"
                + "  ____) / ____ \\| |  | |/ ____ \\ ____) |\n"
                + " |_____/_/    \\_\\_|  |_/_/    \\_\\_____/");
        System.out.println("========================================");
        persistClientes();
        
    }
    
    private boolean persistClientes() {
        try {
            LOG.info("Persistimos Clientes");
            
            Cliente j = new Cliente("Juan");
            Cliente a = new Cliente("Eduardo");
            Cliente e = new Cliente("Ricardo");
            
            clienteRepository.save(e);
            clienteRepository.save(a);
            clienteRepository.save(j);
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los Clientes, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }
    
}
