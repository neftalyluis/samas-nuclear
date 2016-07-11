/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controllers;

import java.util.Collection;
import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.repository.ActivoRepository;
import mx.samas.service.ActivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author samas
 */
@RestController
@RequestMapping("/activo")
public class ActivoController {

    @Autowired
    private ActivoRepository activoRepository;

    @Autowired
    private ActivoService activoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Activo>> getAllActivos() {
        return new ResponseEntity<>((Collection<Activo>) activoRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Activo> getActivoWithId(@PathVariable Long id) {
        return new ResponseEntity<>(activoRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<List<Activo>> findActivoWithName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(activoRepository.findByNombre(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"clavePizarra"})
    public ResponseEntity<List<Activo>> findActivoWithClavePizarra(@RequestParam(value = "clavePizarra") String clavePizarra) {
        return new ResponseEntity<>(activoRepository.findByClavePizarra(clavePizarra), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addActivo(@RequestBody Activo input) {
        return new ResponseEntity<>(activoRepository.save(input), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/vector")
    public ResponseEntity<?> getVectorFromActivo(@PathVariable Long id) {
        return new ResponseEntity<>(activoService.getVectorFromActivo(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vector")
    public ResponseEntity<?> getVectorFromActivo(@RequestParam(value = "clavePizarra") String clavePizarra) {
        return new ResponseEntity<>(activoService.getVectorFromActivo(clavePizarra), HttpStatus.OK);
    }
}
