/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controller;

import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.ActivoPropiedadValor;
import mx.samas.service.ActivoService;
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
 * Este es el controlador que se encargara de manipular Activos en la API REST.
 *
 * @author Neftaly Luis
 */
@RestController
@RequestMapping("/activo")
public class ActivoController {

    @Autowired
    private ActivoService activoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Activo>> getAllActivos() {
        return new ResponseEntity<>(activoService.getAllActivos(), HttpStatus.OK);
    }

    /**
     * Obtenemos el Activo por su ID
     *
     * @param id El ID del Activo
     * @return Las propiedades del activo especificado, en caso de que exista.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Activo> getActivoWithId(@PathVariable Long id) {
        return new ResponseEntity<>(activoService.getById(id), HttpStatus.OK);
    }

    /**
     * Buscamos Activos por su nombre, puede salir mas de un resultado
     *
     * @param name El nombre por el cual se va a buscar el activo
     * @return Uno o varios activos que cumplan con la condicion del nombre
     *
     */
    @RequestMapping(method = RequestMethod.GET, params = {"nombre"})
    public ResponseEntity<List<Activo>> findActivoWithName(@RequestParam(value = "nombre") String name) {
        return new ResponseEntity<>(activoService.getByNombre(name), HttpStatus.OK);
    }

    /**
     *
     * Busca un Activo por su Clave Pizarra
     *
     * @param clavePizarra La clave pizarra en formato XX_XX_XX
     * @return El activo que tenga esa clave pizarra, en caso de que exista
     */
    @RequestMapping(method = RequestMethod.GET, params = {"clavePizarra"})
    public ResponseEntity<Activo> findActivoWithClavePizarra(@RequestParam(value = "clavePizarra") String clavePizarra) {
        return new ResponseEntity<>(activoService.getByClavePizarra(clavePizarra), HttpStatus.OK);
    }

    /**
     * Se crea un nuevo Activo en la DB
     *
     * @param input El Activo en JSON a persistir
     * @return El mismo Activo pero con ya persistido
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Activo> addActivo(@RequestBody Activo input) {
        return new ResponseEntity<>(activoService.createActivo(input), HttpStatus.CREATED);
    }

    /**
     * Se obtienen las propiedades y sus valores (no vectoriales) de un Activo
     * con ID
     *
     * @param id El ID del Activo al cual se le quieren obtener las propiedades
     * @return Todas las propiedades no vectoriales y sus valores que respectan
     * a ese activo
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/propiedades")
    public ResponseEntity<List<ActivoPropiedadValor>> getPropiedadesFromActivo(@PathVariable Long id) {
        return new ResponseEntity<>(activoService.getPropiedadesFromActivo(id), HttpStatus.OK);
    }

}
