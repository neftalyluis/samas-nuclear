/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.HashMap;
import mx.samas.domain.Portafolio;

/**
 *
 * @author samas
 */
public interface RebalanceoService {
    public HashMap<String, Double> presupuestoParaPortafolio(Portafolio p);
}
