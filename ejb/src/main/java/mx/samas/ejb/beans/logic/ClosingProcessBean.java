/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.Blotter;
import mx.samas.ejb.entities.PortfolioAccount;
import mx.samas.ejb.entities.PositionVector;

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
     *
     * Directos son titulo personal, dicese que el titular es el portafolio
     *
     * SELECT POSITION VECTOR WHERE DATE= Día anterior AND NOT COLLATERAL
     * cotejar con el Blotter los flujos de titulos
     *
     *
     * @param accountNumber
     * @param ticker
     * @param d
     * @throws mx.samas.ejb.beans.exceptions.AppException
     */
    public void onDirect(String accountNumber, Date d, String ticker) throws AppException {

        try {
            List<Blotter> lb = br.getBuyAndSellTransactions(accountNumber, d, ticker);
            List<PositionVector> lpv = pvb.getNotInCredit();

            for (Blotter b : lb) {

            }

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
