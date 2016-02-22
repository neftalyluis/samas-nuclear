/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans;

import javax.ejb.Local;
import mx.samas.ejb.entities.Market;
import mx.samas.ejb.entities.PortfolioAccount;

/**
 *
 * @author neftaly
 */
@Local
public interface BlotterRegistrerLocal {

    public boolean depositMoney(Double amount, PortfolioAccount contract);

    public boolean sellAsset();

    public boolean buyAsset();

    public boolean reporto();

}
