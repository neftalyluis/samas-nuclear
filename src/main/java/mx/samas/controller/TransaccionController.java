package mx.samas.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import mx.samas.domain.projection.TransaccionProjection;
import mx.samas.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**Este es el controlador que se encargara de manipular las Transacciones.
 *
 * @author samas
 */
@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TransaccionProjection>> getTransacciones() {
        return new ResponseEntity<>(transaccionService.getAllProjectedTransacciones(), HttpStatus.OK);
    }

    /**
     * @param id Guardara el Id de la Transaccion que se ingrese desde la interfaz.
     * @return Una respuesta de que se encontro con exito el Id de la Transaccion.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<TransaccionProjection> getTransaccionWithId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(transaccionService.findProjectedById(id), HttpStatus.OK);
    }

    /**
     * @param duenoFuente Guardara el nombre de la Fuente de la Transaccion.
     * @return Una respuesta de que se encontro con exito el duenoFuente de la Transaccion.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/duenofuente/{nombre}")
    public ResponseEntity<List<TransaccionProjection>> getTransaccionesWithFuenteNombre(@PathVariable("nombre") String duenoFuente) {
        return new ResponseEntity<>(transaccionService.getAllProjectedWithDuenoFuente(duenoFuente), HttpStatus.OK);
    }

    /**
     * @param nombre Guardara el nombre de la Transaccion.
     * @return Una respuesta de que se encontro con exito el Nombre de la Transaccion.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{nombre}")
    public ResponseEntity<TransaccionProjection> getTransaccionWithNombre(@PathVariable("nombre") String nombre) {
        return new ResponseEntity<>(transaccionService.findProjectedByNombre(nombre), HttpStatus.OK);
    }
}
