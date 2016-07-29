/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controllers;

import java.util.List;
import mx.samas.domain.Estrategia;
import mx.samas.domain.dto.EstrategiaDTO;
import mx.samas.domain.PortafolioModelo;
import mx.samas.domain.dto.PortafolioModeloDTO;
import mx.samas.service.EstrategiaService;
import mx.samas.service.PortafolioModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/estrategia")
public class EstrategiaController {

    @Autowired
    private PortafolioModeloService portafolioModeloService;

    @Autowired
    private EstrategiaService estrategiaService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/actual")
    public ResponseEntity<List<PortafolioModelo>> getActualModel(@PathVariable Long id) {
        return new ResponseEntity<>(portafolioModeloService.getLastPortafolioModeloFromEstrategia(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/modelo/actual")
    public ResponseEntity<List<PortafolioModelo>> createNewModel(@PathVariable Long id, @RequestBody PortafolioModeloDTO modelo) {
        if (modelo.validate()) {
            return new ResponseEntity<>(portafolioModeloService.createNewPortafolioModeloListForEstrategia(id, modelo), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/")
    public ResponseEntity<List<PortafolioModelo>> getAllModels(@PathVariable Long id) {
        return new ResponseEntity<>(portafolioModeloService.getAllModelosFromEstrategia(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Estrategia>> getAllEstrategias() {
        return new ResponseEntity<>(estrategiaService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Estrategia> newEstrategia(EstrategiaDTO input) {
        if (input.validate()) {
            return new ResponseEntity<>(estrategiaService.createFromDTO(input), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
