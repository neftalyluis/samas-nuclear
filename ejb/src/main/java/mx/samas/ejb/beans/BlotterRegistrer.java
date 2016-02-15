/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author neftaly
 */
@Stateless
public class BlotterRegistrer implements BlotterRegistrerLocal {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    @Override
    public boolean depositMoney() {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
