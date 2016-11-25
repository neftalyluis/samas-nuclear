package mx.samas.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
import java.util.List;
import mx.samas.domain.VectorActivo;
import mx.samas.domain.ActivoPropiedadValor;
import mx.samas.service.VectorActivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
public class VectorActivoController {

    @Autowired
    private VectorActivoService vectorActivoService;

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

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/vector/ultimo/propiedades")
    public ResponseEntity<List<ActivoPropiedadValor>> getPropiedadesFromVectorActivo(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/vector/{date}/propiedades")
    public ResponseEntity<List<ActivoPropiedadValor>> getPropiedadesFromVectorActivoWithDate(
            @PathVariable Long id,
            @PathVariable @DateTimeFormat(pattern = "ddMMyyyy") Date fromDate) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //No hace nada porque no hay formato para el Archivo, pero se pone aqui el metodo
    @RequestMapping(value = "/vector/file", method = RequestMethod.POST)
    public ResponseEntity<?> uploadVectorActivoFile(@RequestParam MultipartFile file) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
