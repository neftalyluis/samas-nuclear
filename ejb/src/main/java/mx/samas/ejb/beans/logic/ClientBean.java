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
import mx.samas.ejb.entities.Cliente;

/**
 *
 * @author neftaly
 */
@Stateless
public class ClientBean {

    private static final Logger LOG = Logger.getLogger(ClientBean.class.getName());

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    public List<Cliente> getAllClients() {
        try {
            return em.createQuery("SELECT c FROM Client c").getResultList();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener la lista de clientes, la excepcion es: {0}", e.getMessage());
            return null;
        }
    }
    
    public void persistClient(Cliente c) throws AppException{
        try {
            em.persist(c);
        } catch (Exception e) {
            throw new AppException();
        }
    }

}
