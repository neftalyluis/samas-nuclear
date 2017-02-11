/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Estrategia;
import mx.samas.domain.VectorPortafolioModelo;
import mx.samas.domain.dto.PortafolioModeloDTO;

/**
 *
 * @author samas
 */
public interface VectorPortafolioModeloService {

    public List<VectorPortafolioModelo> getLastPortafolioModeloFromEstrategia(Long id);

    public List<VectorPortafolioModelo> createNewPortafolioModeloListForEstrategia(Long id, PortafolioModeloDTO model);

    public List<VectorPortafolioModelo> getAllModelosFromEstrategia(Long id);

    public List<VectorPortafolioModelo> createPortafolioModeloListFromDTO(PortafolioModeloDTO models, Estrategia e);

    public List<VectorPortafolioModelo> getAll();

}
