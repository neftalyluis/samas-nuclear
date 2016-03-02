/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.entities.Strategy;

/**
 *
 * @author neftaly
 */
@Stateless
public class ClosingProcessBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(ClosingProcessBean.class.getName());

    /**
     * Posiciones en Directo
     *
     * Directos son titulo personal, titular el protafoclio
     * 
     * SELECT POSITION VECTOR WHERE DATE= Día anterior AND NOT COLLATERAL
     *  cotejar con el Blotter los flujos de titulos 
     */
    public void onDirect(Strategy s) {
        
        
    }

    /**
     * Posiciones en credito
     * 
     * titulos que debes o deben
     */
    public void onCredit(Strategy s) {

    }

    /**
     * Posiciones en Flujo Interno
     */
    public void onFlux(Strategy s) {

    }

    public void accrualByServices(Strategy s) {

    }

    public void accrualByPosition(Strategy s) {

    }

    public void accrualByCredit(Strategy s) {

    }

    /**+
     * Se alimenta PriceVector
     */
    public void genericValuation() {

    }
}
