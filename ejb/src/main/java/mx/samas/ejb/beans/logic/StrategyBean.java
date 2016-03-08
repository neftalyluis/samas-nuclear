/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.SliceVector;
import mx.samas.ejb.entities.Strategy;

/**
 *
 * Bean que hace operaciones que se aplican a las estrategias
 *
 * @author neftaly
 */
@Stateless
public class StrategyBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(StrategyBean.class.getName());

//Solo se puede aplicar cuando no existen fungibilidades, o de menos Liquidez
    
    public boolean persistStrategy(Strategy s) {
        List<SliceVector> lsv = s.getSlices();
        Double full = 0.0;
        for (SliceVector slice : lsv) {
            full += slice.getTargetAllocation();
        }

        if (full == 100.0) {
            try {
                em.persist(s);

                return true;
            } catch (Exception e) {
                LOG.log(Level.WARNING, "No pudimos persistir, la excepcion es: {0}", e.getMessage());

                return false;
            }
        } else {
            return false;
        }
    }

    
    public Strategy getStrategyByName(String name) throws AppException {
        try {
            return (Strategy) em.createNamedQuery("Strategy.findByName").setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener la estrategia, la excepcion es: {0}", e.getMessage());
            throw new AppException();
        }
    }
}
