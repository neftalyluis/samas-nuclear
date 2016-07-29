/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Estrategia;
import mx.samas.domain.PortafolioModelo;
import mx.samas.domain.dto.PortafolioModeloDTO;

/**
 *
 * @author samas
 */
public interface PortafolioModeloService {

    public List<PortafolioModelo> getLastPortafolioModeloFromEstrategia(Long id);

    public List<PortafolioModelo> createNewPortafolioModeloListForEstrategia(Long id, PortafolioModeloDTO model);

    public List<PortafolioModelo> getAllModelosFromEstrategia(Long id);

    public List<PortafolioModelo> createPortafolioModeloListFromDTO(PortafolioModeloDTO models, Estrategia e);
}
