package mx.samas.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import mx.samas.domain.Estrategia;
import mx.samas.domain.dto.EstrategiaDTO;
import mx.samas.domain.projection.EstrategiaModeloProjection;
import mx.samas.domain.projection.EstrategiaProjection;
import mx.samas.service.EstrategiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**Este es el controlador que se encargara de manipular las Estrategias.
 *
 * @author samas
 */
@RestController
@RequestMapping("/estrategia")
public class EstrategiaController {

    @Autowired
    private EstrategiaService estrategiaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Estrategia>> getAllEstrategias() {
        return new ResponseEntity<>(estrategiaService.getAll(), HttpStatus.OK);
    }

    /**
     * @param input Variable de tipo EstrategiaDTO que se compone de nombre y modelos.
     * @return Si input se valida, tendremos como respuesta que se creo de manera exitosa la Estrategia.
     *         De lo contrario, la respuesta nos informara de una mala request.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Estrategia> newEstrategia(EstrategiaDTO input) {
        if (input.validate()) {
            return new ResponseEntity<>(estrategiaService.createFromDTO(input), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id Guardara el Id que se ingrese desde la interfaz.
     * @return Una respuesta de que se encontro con exito la Estrategia por su Id. 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<EstrategiaProjection> getById(@PathVariable Long id) {
        return new ResponseEntity<>(estrategiaService.getEstrategiaProjectedWithId(id), HttpStatus.OK);
    }

    /**
     * @param id Guardara el Id que se ingrese desde la interfaz.
     * @return Una respuesta de que se encontro con exito la EstrategiaModelo por su Id. 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/actual")
    public ResponseEntity<List<EstrategiaModeloProjection>> getModeloByEstrategiaId(@PathVariable Long id) {
        return new ResponseEntity<>(estrategiaService.getLastEstrategiaModeloFromIdEstrategia(id), HttpStatus.OK);
    }

}
