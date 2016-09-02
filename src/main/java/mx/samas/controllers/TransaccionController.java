/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controllers;

import java.util.List;
import mx.samas.domain.Transaccion;
import mx.samas.service.TransaccionService;
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
@RequestMapping("/transacciones")
public class TransaccionController {
    
    @Autowired
    private TransaccionService transaccionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Transaccion>> getTransacciones() {
        return new ResponseEntity<>(transaccionService.getAllTransacciones(), HttpStatus.OK);
    }
}
