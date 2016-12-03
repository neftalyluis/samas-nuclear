/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controller;

import java.util.List;
import mx.samas.domain.TipoServicio;
import mx.samas.service.TipoServicioService;
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
@RequestMapping("/tiposervicio")
public class TipoServicioController {

    @Autowired
    private TipoServicioService tipoServicioService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TipoServicio>> getAllTipoServicio() {
        return new ResponseEntity<>(tipoServicioService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TipoServicio> createTipoServicio(@RequestBody TipoServicio ts) {
        return new ResponseEntity<>(tipoServicioService.create(ts), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<TipoServicio> getById(@PathVariable Long id) {
        return new ResponseEntity<>(tipoServicioService.getTipoServicioById(id), HttpStatus.OK);
    }
}
