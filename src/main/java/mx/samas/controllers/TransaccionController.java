/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controllers;

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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getTransacciones() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
