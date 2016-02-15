/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans;

import javax.ejb.Local;
import mx.samas.ejb.entities.Strategy;

/**
 *
 * @author neftaly
 */
@Local
public interface StrategyGeneratorLocal {
    public boolean persistStrategy(Strategy s);
    public Strategy getStrategyByName(String name);
}
