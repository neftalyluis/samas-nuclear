/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.time.LocalDate;
import java.util.Map;
import mx.samas.domain.Grupo;
import mx.samas.domain.Portafolio;

/**
 *
 * @author samas
 */
public interface RebalanceoService {

    public Map<String, Double> presupuestoParaPortafolio(Portafolio p, LocalDate fechaValor);

    public Double poderDeCompra(Portafolio p, LocalDate fechaValor);

    public Map<Grupo, Double> balancePorGrupos(Portafolio p, LocalDate fechaValor);
}
