/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controllers;

import java.util.List;
import mx.samas.domain.Cliente;
import mx.samas.service.ClienteService;
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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return new ResponseEntity<>(clienteService.getAllClientes(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(clienteService.getClienteById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> deleteClienteWithId(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(clienteService.removeClienteById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente c) {
        return new ResponseEntity<>(clienteService.createCliente(c), HttpStatus.CREATED);
    }

}
