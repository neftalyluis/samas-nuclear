/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.bootstrap;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Component
public class EntityBootstraping implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        
        System.out.println("========================================");
        System.out.println("  / ____|  /\\   |  \\/  |   /\\    / ____|\n"
                + " | (___   /  \\  | \\  / |  /  \\  | (___  \n"
                + "  \\___ \\ / /\\ \\ | |\\/| | / /\\ \\  \\___ \\ \n"
                + "  ____) / ____ \\| |  | |/ ____ \\ ____) |\n"
                + " |_____/_/    \\_\\_|  |_/_/    \\_\\_____/");
        System.out.println("========================================");
        return;
    }

}
