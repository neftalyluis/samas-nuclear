/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.controller;

import java.util.List;
import mx.samas.domain.CuentaCorredor;
import mx.samas.domain.dto.CuentaDTO;
import mx.samas.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**Este es el controlador que se encargara de manipular las Cuentas.
 *
 * @author samas
 */
@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CuentaCorredor>> getAllCuentas() {
        return new ResponseEntity<>(cuentaService.getAll(), HttpStatus.OK);
    }

    /**
     * @param id Guardara el Id que se ingrese desde la interfaz.
     * @return Una respuesta de que se encontro con exito el Id. 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CuentaCorredor> getCuentaById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(cuentaService.getById(id), HttpStatus.OK);
    }

    /**
     * @param idCuenta Guardara el Id de la CuentaCorredor que se ingrese desde la interfaz.
     * @return Una respuesta de que se encontro con exito la CuentaCorredor por el Id. 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{idCuenta}")
    public ResponseEntity<CuentaCorredor> getCuentaById(@PathVariable(value = "idCuenta") String idCuenta) {
        return new ResponseEntity<>(cuentaService.getByIdCuenta(idCuenta), HttpStatus.OK);
    }

    /**
     * @param id Guardara el Id de la CuentaCorredor que se desea eliminar ingresandola desde la interfaz.
     * @return Una respuesta de que se elimino con exito la CuentaCorredor usando su Id.
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> deleteCuentaWithId(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(cuentaService.removeById(id), HttpStatus.OK);
    }

    /**
     * @param c Guardara el cuerpo de la request.
     * @return Una respuesta de que se creo con exito una nueva CuentaCorredor.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CuentaCorredor> createCuenta(@RequestBody CuentaDTO c) {
        return new ResponseEntity<>(cuentaService.createFromDto(c), HttpStatus.CREATED);
    }
}
