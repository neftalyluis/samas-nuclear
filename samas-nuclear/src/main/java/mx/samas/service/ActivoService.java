/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Activo;

/**
 *
 * @author samas
 */
public interface ActivoService {
    
    public List<Activo> getAllActivos();
    
    public Activo getById(Long id);
    
    public List<Activo> getByNombre(String nombre);
    
    public Activo getByClavePizarra(String clavePizarra);
    
    public Activo createActivo(Activo input);

}
