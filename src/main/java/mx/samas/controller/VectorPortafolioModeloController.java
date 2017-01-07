package mx.samas.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.time.LocalDate;
import java.util.List;
import mx.samas.domain.VectorPortafolioModelo;
import mx.samas.domain.dto.PortafolioModeloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import mx.samas.service.VectorPortafolioModeloService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**Este es el controlador que se encargara de manipular los VectoresPortafolioModelo.
 *
 * @author samas
 */
@RestController
@RequestMapping("/modelo")
public class VectorPortafolioModeloController {
    // TODO:Checar los metodos de aqui
    @Autowired
    private VectorPortafolioModeloService portafolioModeloService;

    /**
     * @param id Guardara el Id del Modelo actualmente en uso.
     * @return Una respuesta de que se encontro con exito el Id del ultimo PortafolioModelo.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/actual")
    public ResponseEntity<List<VectorPortafolioModelo>> getActualModel(@PathVariable Long id) {
        return new ResponseEntity<>(portafolioModeloService.getLastPortafolioModeloFromEstrategia(id), HttpStatus.OK);
    }

    /**
     * @param id Guardara el Id del nuevo Modelo que se quiera crear.
     * @param modelo Guardara el cuerpo de la request.
     * @return Si se valida el Modelo, se mandara una respuesta de que se creo de manera exitosa el modelo,
     *         de no ser asi, se emitira un mensaje de que la request esta mal.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/modelo/actual")
    public ResponseEntity<List<VectorPortafolioModelo>> createNewModel(@PathVariable Long id, @RequestBody PortafolioModeloDTO modelo) {
        if (modelo.validate()) {
            return new ResponseEntity<>(portafolioModeloService.createNewPortafolioModeloListForEstrategia(id, modelo), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param file Guardara el vectorActivo que se cargara desde (*)
     * @return Una respuesta de que se creo de manera exitosa el archivo. 
     */
    //No hace nada porque no hay formato para el Archivo, pero se pone aqui el metodo
    @RequestMapping(value = "/{id}/modelo/actual/", method = RequestMethod.PUT)
    public ResponseEntity<?> uploadVectorActivoFile(@RequestParam MultipartFile file) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * @param id Guardara el Id de la Estrategia.
     * @return Una respuesta de que se encontraron de manera exitosa todos los Modelos.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/")
    public ResponseEntity<List<VectorPortafolioModelo>> getAllModels(@PathVariable Long id) {
        return new ResponseEntity<>(portafolioModeloService.getAllModelosFromEstrategia(id), HttpStatus.OK);
    }

    /**
     * @param id Guardara el Id del Modelo que se ingrese desde la interfaz.
     * @param fromDate Guardara la fecha actual con el formato Dia/Mes/Año.
     * @return Una respuesta de que se encontro de manera exitosa el Modelo.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{fecha}/modelo/{fecha}")
    public ResponseEntity<?> getModelosWithDate(
            @PathVariable Long id,
            @PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDate fromDate) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
