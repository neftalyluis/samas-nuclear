/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.Date;
import java.util.List;
import mx.samas.domain.Bitacora;

/**
 *
 * @author samas
 */
public interface BitacoraService {

    public List<Bitacora> getBitacoraListWithOperationDate(Date operationDate);

    public List<Bitacora> getBitacoraListWithContractNumber(Long contratoNumero);

    public List<Bitacora> getBitacoraListForActivoWithClavePizarra(String clavePizarra);

    public Bitacora saveBitacoraEntry(Bitacora entry);

    public List<Bitacora> saveBitacoraEntries(List<Bitacora> bitacoraList);

}
