/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controller;

import java.util.List;
import mx.samas.domain.Bitacora;
import mx.samas.domain.BitacoraOrden;
import mx.samas.domain.dto.BitacoraOrdenDTO;
import mx.samas.domain.dto.BitacoraOrdenEjecutorDTO;
import mx.samas.service.BitacoraOrdenService;
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
@RequestMapping("/bitacora/orden")
public class BitacoraOrdenController {

    @Autowired
    private BitacoraOrdenService bitacoraOrdenService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BitacoraOrden>> getAllOrden() {
        return new ResponseEntity<>(bitacoraOrdenService.getAll(), HttpStatus.OK);
    }

    /**
     * @param dto Variable de tipo BitacoraOrdenDTO compuesta por una cadena (nombre), 
     * un booleano (usaActivo) y una lista de long's (transacciones).
     * @return Una respuesta de que la Orden se creo con exito.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BitacoraOrden> createFromDTO(BitacoraOrdenDTO dto) {
        return new ResponseEntity<>(bitacoraOrdenService.createOrden(dto), HttpStatus.OK);
    }

    /**
     * @param id 
     * @return Una respuesta de que se encontro el id de la orden en la bitacora. 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<BitacoraOrden> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(bitacoraOrdenService.findOrdenById(id), HttpStatus.OK);
    }

    /**
     * @param nombre Variable de tipo cadena que almacenara un nombre.
     * @return Una respuesta de que se encontro el nombre de la orden en la bitacora. 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/nombre/{nombre}")
    public ResponseEntity<BitacoraOrden> getByNombre(@PathVariable(value = "nombre") String nombre) {
        return new ResponseEntity<>(bitacoraOrdenService.findOneOrdenByNombre(nombre), HttpStatus.OK);
    }
    
    /**
     * @param orden Variable de tipo BitacoraOrdenEjecutorDTO compuesta de un tipo de variable long (idOperacion),
     * tres cadenas (folioOperacion, clavePizarra y numeroContrato) y una lista (valorTransacciones)
     * @return Una respuesta de que se encontro la orden en la bitacora. 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/execute/")
    public ResponseEntity<List<Bitacora>> execute(@RequestBody BitacoraOrdenEjecutorDTO orden) {
        return new ResponseEntity<>(bitacoraOrdenService.executeOrden(orden), HttpStatus.OK);
    }

}
