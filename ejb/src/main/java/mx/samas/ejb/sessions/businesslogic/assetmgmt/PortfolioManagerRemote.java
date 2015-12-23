/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.sessions.businesslogic.assetmgmt;

import java.util.List;
import mx.samas.ejb.entities.Asset;
import mx.samas.ejb.entities.AssetPropertyType;
import mx.samas.ejb.entities.AssetPropertyValue;

/**
 *
 * @author neftaly
 */
public interface PortfolioManagerRemote {

    public String test(String a);

    public Boolean createAsset(Asset a);

    public Boolean createProperty(AssetPropertyType ap);

    public Boolean createAssetValue(AssetPropertyType ap, AssetPropertyValue av, Asset a);

    public Boolean asignValuetoAsset(Asset a, AssetPropertyType ap, AssetPropertyValue av);

    public List<AssetPropertyType> getProperties();

    public List<AssetPropertyValue> getValues();

    public Boolean createAssetWithProps(List<AssetPropertyType> lap, List<AssetPropertyValue> lav, Asset a);
}
