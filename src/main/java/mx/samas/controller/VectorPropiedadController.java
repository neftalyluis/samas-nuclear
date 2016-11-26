/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controller;

import mx.samas.elastic.service.VectorActivoPropiedadValorService;
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
@RequestMapping("/elastic")
public class VectorPropiedadController {

    @Autowired
    private VectorActivoPropiedadValorService vectorService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<?> getVectorFromActivo() {
        return new ResponseEntity<>(vectorService.findAll(), HttpStatus.OK);
    }

}
