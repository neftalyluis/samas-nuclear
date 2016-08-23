/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controllers;

import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.service.ActivoPropiedadService;
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
@RequestMapping("/propiedades")
public class ActivoPropiedadController {
    
    @Autowired
    private ActivoPropiedadService activoPropiedadService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ActivoPropiedad>> getAllPropiedades() {
        return new ResponseEntity<>(activoPropiedadService.getPropiedades(), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{nombre}")
    public ResponseEntity<ActivoPropiedad> getPropiedadWithName(@PathVariable String nombre) {
        return new ResponseEntity<>(activoPropiedadService.getPropiedadWithNombre(nombre), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ActivoPropiedad> createNewPropiedad(@RequestBody ActivoPropiedad prop) {
        return new ResponseEntity<>(activoPropiedadService.createPropiedad(prop), HttpStatus.CREATED);
    }
}
