/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Cuenta;
import mx.samas.domain.dto.CuentaDTO;

/**
 *
 * @author samas
 */
public interface CuentaService {

    public Cuenta getCuentaByCadena(String cadena);

    public Cuenta createOrUpdateCuenta(Cuenta c);
    
    public Cuenta createFromDto(CuentaDTO c);
    
    public List<Cuenta> createFromDto(List<CuentaDTO> cuentaList);
}
