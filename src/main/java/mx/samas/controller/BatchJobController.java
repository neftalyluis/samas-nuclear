/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controller;

import java.io.File;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**Este es el controlador que se encargara de manipular el Batch.
 *
 * @author samas
 */
@RestController
@RequestMapping("/job")
public class BatchJobController {

    /**
     * Vacio
     */
    public void launchCierreDeDia() {

    }

    /**
     * @param dia Guardara la fecha actual en que se efectuo la operacion.
     * @param archivoPIP Se cargara un archivo y se guardara.
     * @param archivoUsuario Se cargara un archivo y se guardara. 
     */
    public void launchVectorPropiedades(LocalDate dia, File archivoPIP, File archivoUsuario) {

    }

    /**
     * @param contratoId Variable de tipo long que guardara un Id.
     */
    public void launchRebalanceo(Long contratoId) {

    }

    /**
     * Vacio
     */
    public void historyCierreDeDia() {

    }

    /**
     * Vacio
     */
    public void historyVectorPropiedades() {

    }

    /**
     * Vacio
     */
    public void historyRebalanceo() {

    }
    
    /**
     * Vacio
     */
    public void historyAllJobs(){
        
    }
    
    /**
     * Vacio
     */
    public void actualJobsStatus(){
        
    }
}
