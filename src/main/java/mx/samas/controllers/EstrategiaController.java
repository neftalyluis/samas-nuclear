/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controllers;

import java.util.List;
import mx.samas.domain.Estrategia;
import mx.samas.domain.dto.EstrategiaDTO;
import mx.samas.service.EstrategiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private EstrategiaService estrategiaService;

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
