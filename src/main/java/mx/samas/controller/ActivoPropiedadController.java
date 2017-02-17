package mx.samas.controller;

import java.util.List;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.service.ActivoPropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**Este es el controlador que se encargara de manipular ActivosPropiedad.
 * 
 * @author samas
 */
@RestController
@RequestMapping("/propiedades")
public class ActivoPropiedadController {

    @Autowired
    private ActivoPropiedadService activoPropiedadService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ActivoPropiedad>> getAllPropiedades() {
        return new ResponseEntity<>(activoPropiedadService.getPropiedades(), HttpStatus.OK);
    }

    /**
     * @param nombre Guardara el nombre de una Propiedad ingresada desde la interfaz.
     * @return Una respuesta de que se encontro con exito un activoPropiedad por su nombre.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{nombre}")
    public ResponseEntity<ActivoPropiedad> getPropiedadWithName(@PathVariable String nombre) {
        return new ResponseEntity<>(activoPropiedadService.getPropiedadWithNombre(nombre), HttpStatus.OK);
    }

    /**
     * @param prop Guardara el cuerpo de la request del ActivoPropiedad.
     * @return Una respuesta confirmando que se creo con exito la Propiedad.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ActivoPropiedad> createNewPropiedad(@RequestBody ActivoPropiedad prop) {
        return new ResponseEntity<>(activoPropiedadService.createPropiedad(prop), HttpStatus.CREATED);
    }

    /**
     * @param clavePizarra Cadena que obtendra una lista de propiedades de los activos.
     * @return Una respuesta de que obtivieron todas las propiedades de activos de manera exitosa.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/activo/{clavePizarra}")
    public ResponseEntity<List<ActivoPropiedad>> getPropiedadListFromActivo(@PathVariable String clavePizarra) {
        return new ResponseEntity<>(activoPropiedadService.getAllPropiedadesInActivoWithClavePizarra(clavePizarra), HttpStatus.OK);
    }

    /**
     * @param clavePizarra Cadena que obtendra una lista de vectores propiedad de los activos.
     * @return Una respuesta de que obtuvo la propiedad vector de un activo de manera exitosa.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/activo/{clavePizarra}/vectorial")
    public ResponseEntity<List<ActivoPropiedad>> getVectorialPropiedadListFromActivo(@PathVariable String clavePizarra) {
        return new ResponseEntity<>(activoPropiedadService.getVectorialPropiedadesInActivoWithClavePizarra(clavePizarra), HttpStatus.OK);
    }

    /**
     * @param clavePizarra Cadena que obtendra una lista de propiedades estaticas de los activos.
     * @return Una respuesta de que obtuvo la propiedad estatica de un activo de manera exitosa.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/activo/{clavePizarra}/estatica")
    public ResponseEntity<List<ActivoPropiedad>> getEstaticaPropiedadListFromActivo(@PathVariable String clavePizarra) {
        return new ResponseEntity<>(activoPropiedadService.getEstaticaPropiedadesInActivoWithClavePizarra(clavePizarra), HttpStatus.OK);
    }
}
