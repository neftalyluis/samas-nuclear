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

/**
 *
 * @author samas
 */
@RestController
@RequestMapping("/modelo")
public class VectorPortafolioModeloController {
    //TODO:Checar los metodos de aqui
    @Autowired
    private VectorPortafolioModeloService portafolioModeloService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/actual")
    public ResponseEntity<List<VectorPortafolioModelo>> getActualModel(@PathVariable Long id) {
        return new ResponseEntity<>(portafolioModeloService.getLastPortafolioModeloFromEstrategia(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/modelo/actual")
    public ResponseEntity<List<VectorPortafolioModelo>> createNewModel(@PathVariable Long id, @RequestBody PortafolioModeloDTO modelo) {
        if (modelo.validate()) {
            return new ResponseEntity<>(portafolioModeloService.createNewPortafolioModeloListForEstrategia(id, modelo), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //No hace nada porque no hay formato para el Archivo, pero se pone aqui el metodo
    @RequestMapping(value = "/{id}/modelo/actual/", method = RequestMethod.PUT)
    public ResponseEntity<?> uploadVectorActivoFile(@RequestParam MultipartFile file) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/modelo/")
    public ResponseEntity<List<VectorPortafolioModelo>> getAllModels(@PathVariable Long id) {
        return new ResponseEntity<>(portafolioModeloService.getAllModelosFromEstrategia(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{fecha}/modelo/{fecha}")
    public ResponseEntity<?> getModelosWithDate(
            @PathVariable Long id,
            @PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDate fromDate) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
