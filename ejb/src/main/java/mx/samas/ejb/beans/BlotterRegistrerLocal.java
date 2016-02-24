/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans;

import javax.ejb.Local;

/**
 *
 * @author neftaly
 */
@Local
public interface BlotterRegistrerLocal {

    public boolean depositMoney(Double amount, String contract);

    public boolean sellAsset();

    public boolean buyEquity(String ticker, Double price, Long quantity, String contract);
    
    public boolean buyBond();

    public boolean reporto();

}
