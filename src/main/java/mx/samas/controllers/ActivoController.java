/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controllers;

import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.domain.VectorActivo;
import mx.samas.domain.dto.ActivoPropiedadValor;
import mx.samas.service.ActivoService;
import mx.samas.service.VectorActivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author samas
 */
@RestController
@RequestMapping("/activo")
public class ActivoController {

    @Autowired
    private ActivoService activoService;

    @Autowired
    private VectorActivoService vectorActivoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Activo>> getAllActivos() {
        return new ResponseEntity<>(activoService.getAllActivos(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Activo> getActivoWithId(@PathVariable Long id) {
        return new ResponseEntity<>(activoService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"nombre"})
    public ResponseEntity<List<Activo>> findActivoWithName(@RequestParam(value = "nombre") String name) {
        return new ResponseEntity<>(activoService.getByNombre(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"clavePizarra"})
    public ResponseEntity<Activo> findActivoWithClavePizarra(@RequestParam(value = "clavePizarra") String clavePizarra) {
        return new ResponseEntity<>(activoService.getByClavePizarra(clavePizarra), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addActivo(@RequestBody Activo input) {
        return new ResponseEntity<>(activoService.createActivo(input), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/vector")
    public ResponseEntity<?> getVectorFromActivo(@PathVariable Long id) {
        return new ResponseEntity<>(vectorActivoService.getVectorFromActivo(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vector")
    public ResponseEntity<?> getVectorFromActivo(@RequestParam(value = "clavePizarra") String clavePizarra) {
        return new ResponseEntity<>(vectorActivoService.getVectorFromActivo(clavePizarra), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/vector")
    public ResponseEntity<?> addVectorToActivo(@PathVariable Long id, @RequestBody VectorActivo vector) {
        return new ResponseEntity<>(vectorActivoService.addVectorToActivo(id, vector), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/propiedades")
    public ResponseEntity<List<ActivoPropiedadValor>> getPropiedadesFromActivo(@PathVariable Long id) {
        return new ResponseEntity<>(activoService.getPropiedadesFromActivo(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/vector/last/propiedades")
    public ResponseEntity<List<ActivoPropiedadValor>> getPropiedadesFromVectorActivo(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/vector/file", method = RequestMethod.POST)
    public ResponseEntity<?> uploadVectorActivoFile(@RequestParam MultipartFile file) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
