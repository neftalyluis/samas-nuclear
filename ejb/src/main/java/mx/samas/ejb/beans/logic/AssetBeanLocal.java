/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import javax.ejb.Local;
import mx.samas.ejb.entities.Asset;

/**
 *
 * @author neftaly
 */
@Local
public interface AssetBeanLocal {

    public Asset findByTicker(String ticker);

}
