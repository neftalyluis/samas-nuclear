package mx.samas.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.time.LocalDate;
import java.util.List;
import mx.samas.domain.VectorActivo;
import mx.samas.domain.ActivoPropiedadValor;
import mx.samas.service.VectorActivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**Este es el controlador que se encargara de manipular los VectoresActivo.
 *
 * @author samas
 */
@RestController
@RequestMapping("/activo")
public class VectorActivoController {

    @Autowired
    private VectorActivoService vectorActivoService;

    /**
     * @param id Guardara el Id de un activo perteneciente a un vector.
     * @return Una respuesta de que se encontro con exito el Id del activo, para encontrar el vector.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/vector")
    public ResponseEntity<?> getVectorFromActivo(@PathVariable Long id) {
        return new ResponseEntity<>(vectorActivoService.getVectorFromActivo(id), HttpStatus.OK);
    }

    /**
     * @param clavePizarra Guardara la clavePizarra de un activo perteneciente a un vector.
     * @return Una respuesta de que se encontro con exito la clavePizarra del Activo, para encontrar el Vector.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/vector")
    public ResponseEntity<?> getVectorFromActivo(@RequestParam(value = "clavePizarra") String clavePizarra) {
        return new ResponseEntity<>(vectorActivoService.getVectorFromActivo(clavePizarra), HttpStatus.OK);
    }

    /**
     * @param id Guardara el Id del vectorActivo que se quiera crear.
     * @param vector Guardara el cuerpo de la request. 
     * @return Una respuesta de que se creo de manera exitosa el vectorActivo.  
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/vector")
    public ResponseEntity<?> addVectorToActivo(@PathVariable Long id, @RequestBody VectorActivo vector) {
        return new ResponseEntity<>(vectorActivoService.addVectorToActivo(id, vector), HttpStatus.CREATED);
    }

    /**
     * @param id Guardara el Id de la Propiedad que se obtendra desde la interfaz.
     * @return Una respuesta de que se encontro de manera exitosa la propiedad.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/vector/ultimo/propiedades")
    public ResponseEntity<List<ActivoPropiedadValor>> getPropiedadesFromVectorActivo(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param id Guardara el Id del VectorActivo que se ingrese desde la interfaz.
     * @param fromDate Guardara la fecha actual con el formato Dia/Mes/Año.
     * @return Una respuesta de que se encontro de manera exitosa la propiedad.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/vector/{date}/propiedades")
    public ResponseEntity<List<ActivoPropiedadValor>> getPropiedadesFromVectorActivoWithDate(
            @PathVariable Long id,
            @PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDate fromDate) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param file Guardara el vectorActivo que se cargara desde (*)
     * @return Una respuesta de que se creo de manera exitosa el archivo. 
     */
    //No hace nada porque no hay formato para el Archivo, pero se pone aqui el metodo
    @RequestMapping(value = "/vector/file", method = RequestMethod.POST)
    public ResponseEntity<?> uploadVectorActivoFile(@RequestParam MultipartFile file) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
