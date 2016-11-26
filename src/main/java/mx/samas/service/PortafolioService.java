/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Cuenta;
import mx.samas.domain.Portafolio;

/**
 *
 * @author samas
 */
public interface PortafolioService {

    public Portafolio createPortafolio(Portafolio p);
    public List<Portafolio> getPortafoliosFromCuenta(Cuenta c);
}
