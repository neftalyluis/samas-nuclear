/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controllers;

import java.util.List;
import mx.samas.domain.VectorPortafolioModelo;
import mx.samas.domain.dto.PortafolioModeloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import mx.samas.service.VectorPortafolioModeloService;

/**
 *
 * @author samas
 */
@RestController
@RequestMapping("/estrategia")
public class VectorPortafolioModeloController {
    
    @Autowired
    private VectorPortafolioModeloService portafolioModeloService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/actual")
    public ResponseEntity<List<VectorPortafolioModelo>> getActualModel(@PathVariable Long id) {
        return new ResponseEntity<>(portafolioModeloService.getLastPortafolioModeloFromEstrategia(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/modelo/actual")
    public ResponseEntity<List<VectorPortafolioModelo>> createNewModel(@PathVariable Long id, @RequestBody PortafolioModeloDTO modelo) {
        if (modelo.validate()) {
            return new ResponseEntity<>(portafolioModeloService.createNewPortafolioModeloListForEstrategia(id, modelo), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/")
    public ResponseEntity<List<VectorPortafolioModelo>> getAllModels(@PathVariable Long id) {
        return new ResponseEntity<>(portafolioModeloService.getAllModelosFromEstrategia(id), HttpStatus.OK);
    }
}
