/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controller;

import mx.samas.domain.Portafolio;
import mx.samas.domain.dto.PortafolioDTO;
import mx.samas.domain.projection.PortafolioProjection;
import mx.samas.service.PortafolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**Este es el controlador que se encargara de manipular los Portafolios.
 *
 * @author samas
 */
@RestController
@RequestMapping("/portafolio")
public class PortafolioController {

    @Autowired
    private PortafolioService portafolioService;

    /**
     * @param p Guardara los datos que componen a un Portafolio, estos seran ingresados desde la interfaz.
     * @return Una respuesta de que se creo con exito el Portafolio.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Portafolio> createPortafolioFromDto(@RequestBody PortafolioDTO p) {
        return new ResponseEntity<>(portafolioService.createPortafolioFromDto(p), HttpStatus.CREATED);
    }

    /**
     * @param id Guardara el Id (del Portafolio) que se ingresara desde la interfaz.
     * @return Una respuesta de que se encontro con exito el Protafolio por medio de su Id.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<PortafolioProjection> getPortafolioWithId(@PathVariable(value="id") Long id) {
        return new ResponseEntity<>(portafolioService.getProjectedById(id), HttpStatus.OK);
    }

}
