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
import mx.samas.ejb.entities.VectorPortafolioModelo;
import mx.samas.ejb.entities.Estrategia;

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
    public boolean persistStrategy(Estrategia s) {
//        List<SliceVector> lsv = s.getSlices();
//        Double full = 0.0;
//        for (VectorPortafolioModelo slice : lsv) {
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

    public Estrategia getStrategyByName(String name) throws AppException {
        try {
            return (Estrategia) em.createNamedQuery("Estrategia.buscarPorNombre").setParameter("nombre", name).getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener la estrategia, la excepcion es: {0}", e.getMessage());
            throw new AppException();
        }
    }

    public List<Estrategia> getAllStrategies() {
        try {
            Query q = em.createQuery("SELECT s FROM Estrategia s");
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public List<VectorPortafolioModelo> getSlicesFromID(long id) throws AppException {
        try {
            Estrategia s = em.find(Estrategia.class, id);
            return s.getEstrategiaModelo();
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public List<VectorPortafolioModelo> getAllSlices() throws AppException {
        try {
            Query q = em.createQuery("SELECT s FROM VectorPortafolioModelo s");
            return q.getResultList();
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public Estrategia getStrategyByID(long id) throws AppException {
        try {
            return (Estrategia) em.createNamedQuery("Estrategia.buscarPorID").setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener la estrategia, la excepcion es: {0}", e.getMessage());
            throw new AppException();
        }
    }

    public VectorPortafolioModelo getSliceFromStrategyAndId(long strategyId, long sliceId) throws AppException {
        try {
            return (VectorPortafolioModelo) em.createNamedQuery("VectorPortafolioModelo.obtenerModeloPorIDyEstrategia")
                    .setParameter("sliceId", sliceId)
                    .setParameter("strategyId", strategyId)
                    .getSingleResult();
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public List<VectorPortafolioModelo> getActiveSlicesFromStrategy(long strategyId) throws AppException {
        try {
            List<VectorPortafolioModelo> lsv = em.createNamedQuery("VectorPortafolioModelo.obtenerTodosLosModelosPorEstrategia")
                    .setParameter("id", strategyId)
                    .getResultList();

            return lsv;

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException();
        }
    }

    public Estrategia updateStrategy(long id, List<VectorPortafolioModelo> lsv) throws AppException {
        try {
            Estrategia s = em.find(Estrategia.class, id);
            List<VectorPortafolioModelo> old = s.getEstrategiaModelo();
            old.addAll(lsv);
            s.setEstrategiaModelo(old);
            em.persist(s);
            return s;
        } catch (Exception e) {
            throw new AppException();
        }
    }
}
