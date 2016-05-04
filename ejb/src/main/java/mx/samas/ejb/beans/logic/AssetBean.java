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
import mx.samas.ejb.entities.Asset;

/**
 *
 * @author neftaly
 */
@Stateless
public class AssetBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    public Asset findByTicker(String ticker) throws AppException {
        try {
            return (Asset) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", ticker).getSingleResult();
        } catch (Exception e) {
            throw new AppException(404, 404, "Asset no encontrado por el ticker", "", "");
        }
    }

    public Asset findAssetByTicker(String ticker) throws AppException {
        try {
            return (Asset) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", ticker).getSingleResult();
        } catch (Exception e) {
            throw new AppException(404, 404, "Asset no encontrado por el ticker", "", "");
        }
    }

    public void persistAsset(Asset a) throws AppException {
        try {
            em.persist(a);
        } catch (Exception e) {
            throw new AppException();
        }
    }

}
