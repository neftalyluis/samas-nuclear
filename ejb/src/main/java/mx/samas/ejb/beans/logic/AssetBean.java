/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import javax.ejb.Stateless;
import mx.samas.ejb.entities.Asset;

/**
 *
 * @author neftaly
 */
@Stateless
public class AssetBean implements AssetBeanLocal {

    @Override
    public Asset findByTicker(String ticker) {
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
}
