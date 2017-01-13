/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controller;

import java.io.File;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author samas
 */
@RestController
@RequestMapping("/job")
public class BatchJobController {

    public void launchCierreDeDia() {

    }

    public void launchVectorPropiedades(LocalDate dia, File archivoPIP, File archivoUsuario) {

    }

    public void launchRebalanceo(Long contratoId) {

    }

    public void historyCierreDeDia() {

    }

    public void historyVectorPropiedades() {

    }

    public void historyRebalanceo() {

    }
    
    public void historyAllJobs(){
        
    }
    
    public void actualJobsStatus(){
        
    }
}
