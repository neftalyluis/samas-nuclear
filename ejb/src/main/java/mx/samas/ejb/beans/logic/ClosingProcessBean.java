/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.Bitacora;
import mx.samas.ejb.entities.PortafolioCuenta;
import mx.samas.ejb.entities.VectorPosicion;

/**
 *
 * @author neftaly
 */
@Stateless
public class ClosingProcessBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(ClosingProcessBean.class.getName());

    @EJB
    private AssetBean ab;

    @EJB
    private BlotterRegistrer br;

    @EJB
    private PositionVectorBean pvb;

    /**
     * Posiciones en Directo

 Directos son titulo personal, dicese que el titular es el portafolio

 SELECT POSITION VECTOR WHERE DATE= Día anterior AND NOT COLLATERAL
 cotejar con el Bitacora los flujos de titulos
     *
     *
     * @param accountNumber
     * @param ticker
     * @param d
     * @throws mx.samas.ejb.beans.exceptions.AppException
     */
    public void onDirect(String accountNumber, Date d, String ticker) throws AppException {

        try {
//            Igual, Buggy
//            List<Blotter> lb = br.getBuyAndSellTransactions(accountNumber, d, ticker);
            //Query Buggy, checarla mañana
//            List<PositionVector> lpv = pvb.getNotInCredit(d, accountNumber);

//            for (Bitacora b : lb) {
//                LOG.log(Level.INFO, b.getAsset().getName());
//            }

//            HashMap<String, ArrayList<PositionVector>> mewDay = new HashMap<>();
//            
//            if (lpv.isEmpty()) {
//                for (Bitacora b : lb) {
//
//                }
//            }
        } catch (Exception e) {
            throw new AppException();
        }

    }

    /**
     * Posiciones en credito
     *
     * titulos que debes o deben
     *
     * @param pa
     */
    public void onCredit(PortafolioCuenta pa) {

    }

    /**
     * Posiciones en Flujo Interno
     *
     * @param pa
     */
    public void onFlux(PortafolioCuenta pa) {

    }

    public void accrualByServices(PortafolioCuenta pa) {

    }

    public void accrualByPosition(PortafolioCuenta pa) {

    }

    public void accrualByCredit(PortafolioCuenta pa) {

    }

    /**
     * +
     * Se alimenta PriceVector
     */
    public void genericValuation() {

    }
}
