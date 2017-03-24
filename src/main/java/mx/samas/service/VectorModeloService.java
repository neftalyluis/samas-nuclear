/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Modelo;
import mx.samas.domain.VectorModelo;
import mx.samas.domain.dto.PortafolioModeloDTO;

/**
 *
 * @author samas
 */
public interface VectorModeloService {

    public List<VectorModelo> getLastPortafolioModeloFromEstrategia(Long id);

    public List<VectorModelo> createNewPortafolioModeloListForEstrategia(Long id, PortafolioModeloDTO model);

    public List<VectorModelo> getAllModelosFromEstrategia(Long id);

    public List<VectorModelo> createPortafolioModeloListFromDTO(PortafolioModeloDTO models, Modelo e);

    public List<VectorModelo> getAll();

}
