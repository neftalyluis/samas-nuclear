package mx.samas.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.time.LocalDate;
import java.util.List;
import mx.samas.domain.Bitacora;
import mx.samas.service.BitacoraService;
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
 *
 * @author samas
 */
@RestController
@RequestMapping("/bitacora")
public class BitacoraController {

    @Autowired
    private BitacoraService bitacoraService;

    @RequestMapping(method = RequestMethod.GET, value = "/fecha/{operation}")
    public ResponseEntity<List<Bitacora>> getBitacoraListWithDate(@RequestParam(value = "operation") LocalDate operationDate) {
        return new ResponseEntity<>(bitacoraService.getBitacoraListWithOperationDate(operationDate), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contrato/{contractNumber}")
    public ResponseEntity<List<Bitacora>> getBitacoraListWithContractNumber(@PathVariable Long contractNumber) {
        return new ResponseEntity<>(bitacoraService.getBitacoraListWithContractNumber(contractNumber), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clavepizarra/{clavePizarra}")
    public ResponseEntity<List<Bitacora>> getBitacoraListForActivo(@PathVariable String clavePizarra) {
        return new ResponseEntity<>(bitacoraService.getBitacoraListForActivoWithClavePizarra(clavePizarra), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/clavepizarra/{clavePizarra}")
    public ResponseEntity<List<Bitacora>> postNewBitacoraEntry(@RequestBody Bitacora entry) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
