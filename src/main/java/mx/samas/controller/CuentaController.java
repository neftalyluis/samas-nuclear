/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controller;

import java.util.List;
import mx.samas.domain.Cuenta;
import mx.samas.domain.dto.CuentaDTO;
import mx.samas.service.CuentaService;
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
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cuenta>> getAllCuentas() {
        return new ResponseEntity<>(cuentaService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(cuentaService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idCuenta}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable(value = "idCuenta") String idCuenta) {
        return new ResponseEntity<>(cuentaService.getByIdCuenta(idCuenta), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> deleteCuentaWithId(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(cuentaService.removeById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cuenta> createCuenta(@RequestBody CuentaDTO c) {
        return new ResponseEntity<>(cuentaService.createFromDto(c), HttpStatus.CREATED);
    }
}
