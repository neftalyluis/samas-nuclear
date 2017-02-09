/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Cuenta;
import mx.samas.domain.Portafolio;
import mx.samas.domain.dto.PortafolioDTO;
import mx.samas.domain.projection.PortafolioProjection;

/**
 *
 * @author samas
 */
public interface PortafolioService {

    public Portafolio createPortafolio(Portafolio p);
    public Portafolio createPortafolioFromDto(PortafolioDTO p);
    //TODO: Checar porque Spring Data no truena este metodo
    public List<Portafolio> getPortafoliosFromCuenta(Cuenta c);
    public Portafolio getById(Long id);
    public PortafolioProjection getProjectedById(Long id);
    public List<Portafolio> getAll();
}
