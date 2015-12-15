/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.sessions.businesslogic.assetmgmt;

import java.util.List;
import mx.samas.ejb.entities.Asset;
import mx.samas.ejb.entities.AssetProperty;
import mx.samas.ejb.entities.AssetValue;

/**
 *
 * @author neftaly
 */
public interface PortfolioManagerRemote {

    public String test(String a);

    public Boolean createAsset(Asset a);

    public Boolean createProperty(AssetProperty ap);

    public Boolean createAssetValue(AssetProperty ap, AssetValue av, Asset a);

    public Boolean asignValuetoAsset(Asset a, AssetProperty ap, AssetValue av);

    public List<AssetProperty> getProperties();

    public List<AssetValue> getValues();

    public Boolean createAssetWithProps(List<AssetProperty> lap, List<AssetValue> lav, Asset a);
}
