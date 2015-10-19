/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.sessions.system;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.samas.ejb.entities.User;

/**
 *
 * @author neftaly
 */
@Stateless
@Local(UserManagerLocal.class)
@Remote(UserManagerRemote.class)
@RolesAllowed("Administrator")
public class UserManagerBean implements UserManagerLocal {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private void printErrors(String msj, String e) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, msj + ": ", e);
    }

    private void printAction(String msj) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, msj);
    }

    @Override
    public String greet() {
        return "HI FROM SAMAS :DDDDD";
    }

    @Override
    public boolean createUser(User u) {
        boolean r = false;
        try {
            em.persist(u);
            r = true;
        } catch (Exception e) {
            printErrors("Error al persistir un Usuario", e.toString());
        }
        return r;
    }

    @Override
    public User getUser(Long uid) {
        User u = null;
        try {
            Query q = em.createQuery("SELECT u FROM User u WHERE u.id=:myID").setParameter("myID", uid);
            u = (User) q.getSingleResult();
        } catch (Exception e) {
            printErrors("Error al obtener Usuario de ID: " + uid, e.toString());
        }
        return u;
    }

    @Override
    public User getUser(String us) {
        User u = null;
        try {
            Query q = em.createQuery("SELECT u FROM User u WHERE u.email=:mail").setParameter("mail", us);
            u = (User) q.getSingleResult();
        } catch (Exception e) {
            printErrors("Error al obtener Usuario de mail: " + us, e.toString());
        }
        return u;
    }

    @Override
    public boolean updateUser(User u) {
        boolean r = false;
        try {
            em.merge(u);
            r = true;
        } catch (Exception e) {
            printErrors("Error al actualizar un Usuario", e.toString());
        }
        return r;
    }

    @Override
    public boolean removeUser(User u) {
        boolean r = false;
        try {
            em.remove(u);
            r = true;
        } catch (Exception e) {
            printErrors("Error al remover un Usuario", e.toString());
        }
        return r;
    }

}
