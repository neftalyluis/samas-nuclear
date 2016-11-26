/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Cuenta;
import mx.samas.domain.Portafolio;
import mx.samas.repository.PortafolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class PortafolioServiceImpl implements PortafolioService {

    @Autowired
    private PortafolioRepository portafolioRepository;
    
    @Override
    public Portafolio createPortafolio(Portafolio p) {
        return portafolioRepository.save(p);
    }

    @Override
    public List<Portafolio> getPortafoliosFromCuenta(Cuenta c) {
        return portafolioRepository.findByCorredores(c);
    }

}
