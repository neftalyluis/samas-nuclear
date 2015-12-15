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
public interface PortfolioManagerLocal {

    String test(String a);

    Boolean createAsset(Asset a);

    Boolean createProperty(AssetProperty ap);

    Boolean createAssetValue(AssetProperty ap, AssetValue av, Asset a);

    Boolean asignValuetoAsset(Asset a, AssetProperty ap, AssetValue av);

    List<AssetProperty> getProperties();

    List<AssetValue> getValues();

    Boolean createAssetWithProps(List<AssetProperty> lap, List<AssetValue> lav, Asset a);

}
