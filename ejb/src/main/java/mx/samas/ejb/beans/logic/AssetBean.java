/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.Activo;

/**
 *
 * @author neftaly
 */
@Stateless
public class AssetBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    public Activo findByTicker(String ticker) throws AppException {
        try {
            return (Activo) em.createNamedQuery("Activo.buscarPorClavePizarra").setParameter("clavePizarra", ticker).getSingleResult();
        } catch (Exception e) {
            throw new AppException(404, 404, "Asset no encontrado por el ticker", "", "");
        }
    }

    public Activo findAssetByTicker(String ticker) throws AppException {
        try {
            return (Activo) em.createNamedQuery("Activo.buscarPorClavePizarra").setParameter("clavePizarra", ticker).getSingleResult();
        } catch (Exception e) {
            throw new AppException(404, 404, "Asset no encontrado por el ticker", "", "");
        }
    }

    public void persistAsset(Activo a) throws AppException {
        try {
            em.persist(a);
        } catch (Exception e) {
            throw new AppException();
        }
    }

}
