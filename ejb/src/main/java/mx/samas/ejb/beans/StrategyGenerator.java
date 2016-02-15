/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.entities.SliceVector;
import mx.samas.ejb.entities.Strategy;
import mx.samas.ejb.exceptions.NotACompleteStrategyException;

/**
 *
 * Bean que hace operaciones que se aplican a las estrategias
 *
 * @author neftaly
 */
@Stateless
public class StrategyGenerator implements StrategyGeneratorLocal {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(StrategyGenerator.class.getName());

//Solo se puede aplicar cuando no existen fungibilidades, o de menos Liquidez
    @Override
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
            throw new NotACompleteStrategyException();
        }
    }

    @Override
    public Strategy getStrategyByName(String name) {
        try {
            return (Strategy) em.createNamedQuery("Strategy.findByName").setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener la estrategia, la excepcion es: {0}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
