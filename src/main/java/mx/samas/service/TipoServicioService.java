/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.TipoServicio;

/**
 *
 * @author samas
 */
public interface TipoServicioService {

    public TipoServicio getTipoServicioById(Long id);
    
    public TipoServicio create(TipoServicio ts);
    
    public List<TipoServicio> getAll();
}
