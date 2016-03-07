/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import mx.samas.ejb.entities.Blotter;
import mx.samas.ejb.entities.PortfolioAccount;

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
     * cotejar con el Blotter los flujos de titulos
     *
     *
     * @param pa
     * @param d
     */
    public void onDirect(PortfolioAccount pa, Date d) {
        
        List<Blotter> lb = em.createNamedQuery("Blotter.BuyAndSellFromDateAndAccountWithAsset")
                .setParameter("account", pa)
                .setParameter("input", d, TemporalType.DATE)
                .getResultList();
    }

    /**
     * Posiciones en credito
     *
     * titulos que debes o deben
     *
     * @param pa
     */
    public void onCredit(PortfolioAccount pa) {
        
    }

    /**
     * Posiciones en Flujo Interno
     *
     * @param pa
     */
    public void onFlux(PortfolioAccount pa) {
        
    }
    
    public void accrualByServices(PortfolioAccount pa) {
        
    }
    
    public void accrualByPosition(PortfolioAccount pa) {
        
    }
    
    public void accrualByCredit(PortfolioAccount pa) {
        
    }

    /**
     * +
     * Se alimenta PriceVector
     */
    public void genericValuation() {
        
    }
}
