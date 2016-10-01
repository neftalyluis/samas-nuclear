/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Portafolio;
import mx.samas.domain.VectorPosicion;
import mx.samas.repository.VectorPosicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class VectorPosicionServiceImpl implements VectorPosicionService{

    @Autowired
    private VectorPosicionRepository vectorPosicionRepository;
    
    @Override
    public List<VectorPosicion> getLastPosicionesFromPortafolio(Portafolio p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
