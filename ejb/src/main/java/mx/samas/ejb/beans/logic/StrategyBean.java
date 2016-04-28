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
import javax.persistence.Query;
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
//        List<SliceVector> lsv = s.getSlices();
//        Double full = 0.0;
//        for (SliceVector slice : lsv) {
//            full += slice.getTargetAllocation();
//        }
//
//        if (full == 100.0) {
//            try {
//                em.persist(s);
//
//                return true;
//            } catch (Exception e) {
//                LOG.log(Level.WARNING, "No pudimos persistir, la excepcion es: {0}", e.getMessage());
//
//                return false;
//            }
//        } else {
//            return false;
//        }

        em.persist(s);
        return true;
    }

    public Strategy getStrategyByName(String name) throws AppException {
        try {
            return (Strategy) em.createNamedQuery("Strategy.findByName").setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener la estrategia, la excepcion es: {0}", e.getMessage());
            throw new AppException();
        }
    }

    public List<Strategy> getAllStrategies() {
        try {
            Query q = em.createQuery("SELECT s FROM Strategy s");
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public List<SliceVector> getSlicesFromID(long id) throws AppException {
        try {
            Strategy s = em.find(Strategy.class, id);
            return s.getSlices();
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public List<SliceVector> getAllSlices() throws AppException {
        try {
            Query q = em.createQuery("SELECT s FROM SliceVector s");
            return q.getResultList();
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public Strategy getStrategyByID(long id) throws AppException {
        try {
            return (Strategy) em.createNamedQuery("Strategy.findByID").setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener la estrategia, la excepcion es: {0}", e.getMessage());
            throw new AppException();
        }
    }

    public SliceVector getSliceFromStrategyAndId(long strategyId, long sliceId) throws AppException {
        try {
            return (SliceVector) em.createNamedQuery("SliceVector."
                    + "getSliceWithIdAndStrategyId")
                    .setParameter("sliceId", sliceId)
                    .setParameter("strategyId", strategyId)
                    .getSingleResult();
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public List<SliceVector> getActiveSlicesFromStrategy(long strategyId) throws AppException {
        try {
            List<SliceVector> lsv = em.createNamedQuery("SliceVector.getLastSlicesFromStrategy")
                    .setParameter("strategyId", strategyId)
                    .getResultList();

            return lsv;

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException();
        }
    }

    public Strategy updateStrategy(long id, List<SliceVector> lsv) throws AppException {
        try {
            Strategy s = em.find(Strategy.class, id);
            List<SliceVector> old = s.getSlices();
            old.addAll(lsv);
            s.setSlices(old);
            em.persist(s);
            return s;
        } catch (Exception e) {
            throw new AppException();
        }
    }
}
