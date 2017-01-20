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
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**Este es el controlador que se encargara de manipular Activos.
 * 
 * @author samas
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
     * @return Una respues de que se creo correctamente el arreglo. 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/ayylmao/")
    public ResponseEntity<String> test(){
        INDArray nd = Nd4j.create(new float[]{1,2,3,4},new int[]{2,2});
        return new ResponseEntity<>(nd.toString(), HttpStatus.OK);
    }

    /**
     * @param id Guardara el id que se ingrese desde la interfaz.
     * @return Una respuesta de que se encontro con exito un activo por su id.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Activo> getActivoWithId(@PathVariable Long id) {
        return new ResponseEntity<>(activoService.getById(id), HttpStatus.OK);
    }

    /**
     * @param name Guardara el nombre que se ingrese desde la interfaz.
     * @return Una respuesta de que se encontro con exito un activo por su nombre.
     */
    @RequestMapping(method = RequestMethod.GET, params = {"nombre"})
    public ResponseEntity<List<Activo>> findActivoWithName(@RequestParam(value = "nombre") String name) {
        return new ResponseEntity<>(activoService.getByNombre(name), HttpStatus.OK);
    }

    /**
     * @param clavePizarra Guardara la clavePizarra que se ingrese desde de la interfaz. 
     * @return Una respuesta de que se encontro con exito un activo por su clavePizarra.
     */
    @RequestMapping(method = RequestMethod.GET, params = {"clavePizarra"})
    public ResponseEntity<Activo> findActivoWithClavePizarra(@RequestParam(value = "clavePizarra") String clavePizarra) {
        return new ResponseEntity<>(activoService.getByClavePizarra(clavePizarra), HttpStatus.OK);
    }

    /**
     * @param input Guardara el cuerpo de la request
     * @return Una respuesta de que se agrego de forma exitosa el input (cuerpo de la request)
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Activo> addActivo(@RequestBody Activo input) {
        return new ResponseEntity<>(activoService.createActivo(input), HttpStatus.CREATED);
    }

    /**
     * @param id Guardara el id (propiedades) que se obtenga desde la interfaz.
     * @return Una respuesta cuando se obtienen Propiedades del Activo (id) correctamente.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/propiedades")
    public ResponseEntity<List<ActivoPropiedadValor>> getPropiedadesFromActivo(@PathVariable Long id) {
        return new ResponseEntity<>(activoService.getPropiedadesFromActivo(id), HttpStatus.OK);
    }

}
