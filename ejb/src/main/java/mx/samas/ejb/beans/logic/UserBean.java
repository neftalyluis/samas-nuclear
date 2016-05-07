/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.samas.ejb.entities.Usuario;

/**
 *
 * @author neftaly
 */
@Stateless
public class UserBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;
    
    public List<Usuario> getAllUsers(){
        Query q = em.createQuery("SELECT u FROM Usuario u");
        return q.getResultList();
    }
    
}
