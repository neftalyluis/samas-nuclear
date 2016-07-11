/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controllers;

import java.util.Collection;
import mx.samas.domain.PortafolioModelo;
import mx.samas.domain.PortafolioModeloDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author samas
 */
@RestController
@RequestMapping("estrategia")
public class EstrategiaController {
    

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/actual")
    public ResponseEntity<Collection<PortafolioModelo>> getActualModel(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/modelo/actual")
    public ResponseEntity<Collection<PortafolioModelo>> createNewModel(@PathVariable Long id, @RequestBody PortafolioModeloDTO modelo) {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/")
    public ResponseEntity<Collection<PortafolioModelo>> getAllModels(@PathVariable Long id) {
        return null;
    }

}
