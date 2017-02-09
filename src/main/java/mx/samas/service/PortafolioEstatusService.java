/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.PortafolioEstatus;

/**
 *
 * @author samas
 */
public interface PortafolioEstatusService {

    public void createPortafolioEstatus(PortafolioEstatus pe);

    public PortafolioEstatus getPortafolioEstatusByNombre(String nombre);
    
    public PortafolioEstatus getEstatusInicial();
    
    public List<PortafolioEstatus> getAll();

}
