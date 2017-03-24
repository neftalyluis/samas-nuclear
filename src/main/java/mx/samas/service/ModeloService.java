/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Modelo;
import mx.samas.domain.dto.EstrategiaDTO;
import mx.samas.domain.projection.EstrategiaModeloProjection;
import mx.samas.domain.projection.EstrategiaProjection;

/**
 *
 * @author samas
 */
public interface ModeloService {

    public List<Modelo> getAll();

    public Modelo createFromDTO(EstrategiaDTO estrategia);

    public void createEstrategia(Modelo e);
    
    public Modelo getEstrategiaWithId(Long id);
    
    public EstrategiaProjection getEstrategiaProjectedWithId(Long id);
    
    public List<EstrategiaModeloProjection> getLastEstrategiaModeloFromIdEstrategia(Long id);

}
