/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.timers;

import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import mx.samas.ejb.entities.Bond;

/**
 *
 * @author neftaly
 */
@Stateless
public class OnDeployBootstraping {
    
    private static final Logger log = Logger.getLogger(OnDeployBootstraping.class.getName());
    
    @Schedule(hour = "*", minute = "*", persistent = false)
    protected void init(Timer timer) {
        log.info("SAMAS Bootstrap");
        doTheBootstrap();
        timer.cancel();
    }
    
    private void doTheBootstrap() {
        
    }
    
    private void createAssets() {
        log.info("Persistiendo Entidades Iniciales");
        
        Bond udibono40 = new Bond();
//        udibono40.setCashflowDate(cashflowDate);
//        udibono40.setComission(Boolean.FALSE);
//        udibono40.setCurrencyDenomination(currencyDenomination);
//        udibono40.setDayCount(dayCount);
//        udibono40.setIsin(isin);
//        udibono40.setIssuer(issuer);
//        udibono40.setMaturityDate(maturityDate);
//        udibono40.setName(name);
//        udibono40.setReferenceRate(referenceRate);
//        udibono40.setSecurityClass(securityClass);
//        udibono40.setSeries(series);
//        udibono40.setSettlementTimes(settlementTimes);
//        udibono40.setSpreadFixed(Double.NaN);
//        udibono40.setTermStructure(termStructure);
//        udibono40.setTickSize(Double.MIN_VALUE);
//        udibono40.setTicker(ticker);
//        
        
        Bond udibono22 = new Bond();
        Bond udibono16 = new Bond();
        Bond bonos42 = new Bond();
        Bond bonos24 = new Bond();
        Bond bonos16 = new Bond();
        
    }
    
}
