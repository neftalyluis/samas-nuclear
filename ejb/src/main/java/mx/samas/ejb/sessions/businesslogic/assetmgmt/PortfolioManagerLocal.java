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
public interface PortfolioManagerLocal {

    String test(String a);

    Boolean createAsset(Asset a);

    Boolean createProperty(AssetPropertyType ap);

    Boolean createAssetValue(AssetPropertyType ap, AssetPropertyValue av, Asset a);

    Boolean asignValuetoAsset(Asset a, AssetPropertyType ap, AssetPropertyValue av);

    List<AssetPropertyType> getProperties();

    List<AssetPropertyValue> getValues();

    Boolean createAssetWithProps(List<AssetPropertyType> lap, List<AssetPropertyValue> lav, Asset a);

}
