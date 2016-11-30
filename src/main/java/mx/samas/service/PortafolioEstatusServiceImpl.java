/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.PortafolioEstatus;
import mx.samas.repository.PortafolioEstatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class PortafolioEstatusServiceImpl implements PortafolioEstatusService {

    @Autowired
    private PortafolioEstatusRepository portafolioEstatusRepository;

    @Override
    public void createPortafolioEstatus(PortafolioEstatus pe) {
        portafolioEstatusRepository.save(pe);
    }

    @Override
    public PortafolioEstatus getPortafolioEstatusByNombre(String nombre) {
        return portafolioEstatusRepository.findOneByNombre(nombre);
    }

    @Override
    public PortafolioEstatus getEstatusInicial() {
        return portafolioEstatusRepository.getEstatusInicial();
    }

}
