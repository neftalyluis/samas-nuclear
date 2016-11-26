/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.DuenoFuente;

/**
 *
 * @author samas
 */
public interface DuenoFuenteService {

    public void createDuenoFuente(DuenoFuente source);

    public DuenoFuente findDuenoFuenteByName(String name);

}
