/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.Emisor;

/**
 *
 * @author neftaly
 */
@Stateless
public class IssuerBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(IssuerBean.class.getName());

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Emisor getIssuerByCode(String code) {
        try {
            return (Emisor) em.createNamedQuery("Emisor.buscarPorCodigo").setParameter("codigo", code).getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener el Issuer, la excepcion es: {0}", e.getMessage());
            return null;
        }
    }
    
    public void persistIssuer(Emisor i) throws AppException{
        try {
            em.persist(i);
        } catch (Exception e) {
            throw new AppException();
        }
    }


}
