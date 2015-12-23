/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.sessions.businesslogic.assetmgmt;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.samas.ejb.entities.Asset;
import mx.samas.ejb.entities.AssetPropertyType;
import mx.samas.ejb.entities.AssetPropertyValue;

/**
 *
 * @author neftaly
 */
@Stateless
@Local(PortfolioManagerLocal.class)
@Remote(PortfolioManagerRemote.class)
@PermitAll
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PortfolioManagerBean implements PortfolioManagerLocal {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    @Override
    public String test(String a) {
        return "Hola" + a + "desde Glassfish";
    }

    @Override
    public Boolean createAsset(Asset a) {
        try {
            em.persist(a);
            return true;
        } catch (Exception e) {
            System.out.println("Error en persistir");
            return false;
        }
    }

    @Override
    public Boolean createProperty(AssetPropertyType ap) {
        try {
            em.persist(ap);
            return true;
        } catch (Exception e) {
            System.out.println("Error en persistir");
            return false;
        }
    }

    @Override
    public Boolean createAssetValue(AssetPropertyType ap, AssetPropertyValue av, Asset a) {
        try {
            em.persist(a);
            em.persist(ap);
            em.persist(av);
            return true;
        } catch (Exception e) {
            System.out.println("Error en persistir");
            return false;
        }
    }

    @Override
    public Boolean asignValuetoAsset(Asset a, AssetPropertyType ap, AssetPropertyValue av) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AssetPropertyType> getProperties() {
        try {
            Query q = em.createQuery("SELECT ap FROM AssetProperty ap");
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("Error");
            return null;
        }
    }

    @Override
    public List<AssetPropertyValue> getValues() {
        try {
            Query q = em.createQuery("SELECT av FROM AssetValue av");
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("Error");
            return null;
        }
    }

    @Override
    public Boolean createAssetWithProps(List<AssetPropertyType> lap, List<AssetPropertyValue> lav, Asset a) {
        try {

            em.persist(a);

            for (AssetPropertyType ap : lap) {
                em.persist(ap);
            }

            for (AssetPropertyValue av : lav) {
                em.persist(av);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
