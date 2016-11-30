/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controller;

import java.util.List;
import mx.samas.domain.Banco;
import mx.samas.service.BancoService;
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
@RequestMapping("/banco")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Banco>> getAllBancos() {
        return new ResponseEntity<>(bancoService.getAllBanco(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Banco> createBanco(@RequestBody Banco b) {
        return new ResponseEntity<>(bancoService.createBanco(b), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"nombre"})
    public ResponseEntity<Banco> getBancoByNombre(@RequestParam(value = "nombre") String nombre) {
        return new ResponseEntity<>(bancoService.getByNombre(nombre), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> removeBanco(@PathVariable Long id) {
        return new ResponseEntity<>(bancoService.removeBancoById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Banco> updateBanco(@RequestBody Banco b, @PathVariable Long id) {
        return new ResponseEntity<>(bancoService.updateBancoById(b), HttpStatus.OK);
    }
}
