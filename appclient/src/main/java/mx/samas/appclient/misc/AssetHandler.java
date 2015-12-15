/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.appclient.misc;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import static mx.samas.appclient.main.Main.pmr;
import mx.samas.ejb.entities.Asset;
import mx.samas.ejb.entities.AssetProperty;
import mx.samas.ejb.entities.AssetValue;

/**
 *
 * @author neftaly
 */
public class AssetHandler {

    public boolean firstTest() {
        Asset a = new Asset();
        a.setName("Prueba, soy un Stock");

        AssetProperty ap = new AssetProperty();
        ap.setName("TICKER");
        ap.setType("String");

        AssetValue av = new AssetValue();
        av.setProperty(ap);
        av.setAsset(a);
        String ti = "AAPL";
        av.setObjectValue(ti);
        try {
            System.out.println(pmr.createAssetValue(ap, av, a));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean secondTest() {
        try {
            List<AssetProperty> lap = pmr.getProperties();
            for (AssetProperty ap : lap) {
                System.out.println(ap.getName() + ": " + ap.getType() + " :" + ap.getDescription());
            }

            List<AssetValue> lav = pmr.getValues();
            for (AssetValue av : lav) {
                Serializable o = av.getObjectValue();
                if (o instanceof String) {
                    String s = (String) o;
                    System.out.println(av.getAsset().getName() + ":" + av.getProperty().getName() + ":" + s);
                } else if (o instanceof Long) {
                    Long s = (Long) o;
                    System.out.println(av.getAsset().getName() + ":" + av.getProperty().getName() + ":" + s);
                } else if (o instanceof Double) {
                    Double s = (Double) o;
                    System.out.println(av.getAsset().getName() + ":" + av.getProperty().getName() + ":" + s);
                } else if (o instanceof Date) {
                    Date s = (Date) o;
                    System.out.println(av.getAsset().getName() + ":" + av.getProperty().getName() + ":" + s);
                } else if (o instanceof Boolean) {
                    Boolean s = (Boolean) o;
                    System.out.println(av.getAsset().getName() + ":" + av.getProperty().getName() + ":" + s);
                } else {
                    System.out.println(av.getAsset().getName() + ":" + av.getProperty().getName() + ": KEK, no valor para ti, :v");
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean thirdTest() {

        List<AssetProperty> lap = new LinkedList<>();
        List<AssetValue> lav = new LinkedList<>();

        Double numero = 678.6789;
        Long otronumero = (long) 874512;
        Date fecha = new Date();
        Boolean boleano = true;
        String cadena = "HOLI";

        Asset a = new Asset();
        a.setName("Prueba, soy otro Stock");

        AssetProperty aps = new AssetProperty();
        aps.setName("TICKER");
        aps.setType("String");

        AssetValue avs = new AssetValue();
        avs.setAsset(a);
        avs.setProperty(aps);
        avs.setObjectValue(cadena);

        lap.add(aps);
        lav.add(avs);

        AssetProperty apd = new AssetProperty();
        apd.setName("FECHA");
        apd.setType("DATE");

        AssetValue avd = new AssetValue();
        avd.setAsset(a);
        avd.setProperty(apd);
        avd.setObjectValue(fecha);

        lap.add(apd);
        lav.add(avd);

        AssetProperty apdo = new AssetProperty();
        apdo.setName("VALOR FLOTANTE");
        apdo.setType("DOUBLE");

        AssetValue avdo = new AssetValue();
        avdo.setAsset(a);
        avdo.setProperty(apdo);
        avdo.setObjectValue(numero);

        lap.add(apdo);
        lav.add(avdo);

        AssetProperty apb = new AssetProperty();
        apb.setName("ESTA ACTIVO?");
        apb.setType("BOOLEAN");

        AssetValue avb = new AssetValue();
        avb.setAsset(a);
        avb.setProperty(apb);
        avb.setObjectValue(boleano);

        lap.add(apb);
        lav.add(avb);

        AssetProperty apl = new AssetProperty();
        apl.setName("CUANTOS?");
        apl.setType("LONG");

        AssetValue avl = new AssetValue();
        avl.setAsset(a);
        avl.setProperty(apl);
        avl.setObjectValue(otronumero);

        lap.add(apl);
        lav.add(avl);

        try {
            pmr.createAssetWithProps(lap, lav, a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
