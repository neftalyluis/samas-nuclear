/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.CuentaCorredor;
import mx.samas.domain.dto.CuentaDTO;

/**
 *
 * @author samas
 */
public interface CuentaService {

    public CuentaCorredor getByIdCuenta(String idCuenta);

    public CuentaCorredor createOrUpdateCuenta(CuentaCorredor c);
    
    public CuentaCorredor createFromDto(CuentaDTO c);
    
    public List<CuentaCorredor> createFromDto(List<CuentaDTO> cuentaList);
    
    public List<CuentaCorredor> getAll();
    
    public CuentaCorredor getById(Long id);
    
    public Boolean removeById(Long id);
    
    public List<String> getAllIdCuenta();
}
