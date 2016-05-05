/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.timers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.samas.ejb.entities.Activo;
import mx.samas.ejb.entities.TipoValor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;

/**
 *
 * @author neftaly
 */
@Stateless
public class CheckAssets {

    Map<String, String> assetType;
    Map<String, Activo> assetProps;
    SimpleDateFormat formatterTyC = new SimpleDateFormat("yyyy/MM/dd");
    //SimpleDateFormat formatterTyC = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    java.util.Date date = new java.util.Date();
    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;
    Calendar cal = Calendar.getInstance();

    public void getAssetFromCSV() {
        Map<String, Activo> listAssets = new HashMap<>();
        DocumentConnection dc = new DocumentConnection();
        List<String> routes;
        char delimiter = '\'';
        routes = dc.listAllCSVFiles();
        List<TipoValor> lsc = getSecurityList();
        for (String r : routes) {
            System.out.println(r);
            Reader reader = null;
            try {
                final URL url = new URL("file://" + r);
                reader = new InputStreamReader(new BOMInputStream(url.openStream()), "UTF-8");
                final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader().withQuote(delimiter));
                try {
                    int count = 0;
                    for (final CSVRecord record : parser) {
                        try {

                            String sc = record.get(1);
                            for (TipoValor s : lsc) {
                                if (s.getCode().equals(sc)) {

                                }
                            }
//                            System.out.println(record.get(1) + "_" + record.get(2) + "_" + record.get(3).replace("\"", "").replace("\'", ""));
//                            System.out.println(record.get(9).replace("\"", "").replace("\'", ""));
//                            System.out.println(record.get(1).replace("\"", "").replace("\'", ""));
//                            System.out.println(record.get(2).replace("\"", "").replace("\'", ""));
//                            System.out.println(record.get(3).replace("\"", "").replace("\'", ""));
                            System.out.println(record.get(18).replace("\"", "").replace("\'", ""));

//                            Activo value;
//                            String key = record.get(1) + "_" + record.get(2) + "_" + record.get(3).replace("\"", "").replace("\'", "");
//                            String tipovalor = record.get(1).replace("\"", "").replace("\'", "");
//
//                            //Ponemos en nuestro objeto los valores que estan en nuestro CSV
//                            value.setTicker(key);
//                            value.setName(record.get(9).replace("\"", "").replace("\'", ""));
//                            value.setTv(record.get(1).replace("\"", "").replace("\'", ""));
//                            value.setIssuer(record.get(2).replace("\"", "").replace("\'", ""));
//                            value.setSeries(record.get(3).replace("\"", "").replace("\'", ""));
//                            value.setCurrencyDenomination(record.get(18).replace("\"", "").replace("\'", ""));
//                            for (Map.Entry<String, String> entry : assetType.entrySet()) {
//                                if (tipovalor.equals(entry.getKey())) {
//                                    value.setType(entry.getValue());
//                                }
//                            }
//                            listAssets.put(key, value);
                        } catch (NullPointerException e) {
                        } catch (ArrayIndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    parser.close();
                    reader.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(PrecioTimer.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(PrecioTimer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
//        getAssetPropertiesFromTyC(listAssets);
//        if (pushAssetsToDB(listAssets)) {
//            System.out.println("Assets en DB");
//        } else {
//            System.out.println("Nope");
//        }
//        listAssets.clear();
    }

    private List<TipoValor> getSecurityList() {
        Query q = em.createQuery("SELECT s FROM SecurityClass s");
        return q.getResultList();
    }

    private Map<String, Activo> getAssetMap() {
        Map<String, Activo> ma = new HashMap<>();
        Query q = em.createQuery("SELECT a FROM Asset a");
        List<Activo> la = q.getResultList();
        for (Activo a : la) {
//            ma.put(a.getTicker().getTickerValue(), a);
        }
        return ma;
    }

    private String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
//
//    public Map<String, Activo> getAssetPropertiesFromTyC(Map<String, Activo> ma) {
//        assetType = getAssetType();
//
////        MySQLConnection samas = new MySQLConnection("samas", "127.0.0.1", 3306, "samas", "test");
////        ResultSet assets = samas.makeQuery("SELECT ID, CODE FROM TERMSTRUCTURE");
////        Map<String, Long> mapTermStructure = new HashMap<>();
////        try {
////            while (assets.next()) {
////                mapTermStructure.put(deAccent(assets.getString("CODE")).replaceAll("[^A-Za-z0-9]", "").toLowerCase(), assets.getLong("ID"));
////            }
////            samas.closeQuery();
////        } catch (SQLException ex) {
////            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
////        }
////
////        assets = samas.makeQuery("SELECT ID, NAME FROM BONDCOLLATERAL");
////        Map<String, Long> mapBondCollateral = new HashMap<>();
////        try {
////            while (assets.next()) {
////                mapBondCollateral.put(deAccent(assets.getString("NAME")).replaceAll("[^A-Za-z0-9]", "").toLowerCase(), assets.getLong("ID"));
////            }
////            samas.closeQuery();
////        } catch (SQLException ex) {
////            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
////        }
////
////        assets = samas.makeQuery("SELECT ID, CODE FROM REFERENCERATE");
////        Map<String, Long> mapReferenceRate = new HashMap<>();
////        try {
////            while (assets.next()) {
////                mapReferenceRate.put(deAccent(assets.getString("CODE")).replaceAll("[^A-Za-z0-9]", "").toLowerCase(), assets.getLong("ID"));
////            }
////            samas.closeQuery();
////        } catch (SQLException ex) {
////            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        String r = "/home/neftaly/tyc.csv";
//        Reader reader = null;
//        try {
//            final URL url = new URL("file://" + r);
//            System.out.println(r);
//            reader = new InputStreamReader(new BOMInputStream(url.openStream()), "UTF-8");
//            final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader().withDelimiter('|'));
//            try {
//                int count = 0;
//                for (final CSVRecord record : parser) {
//                    count++;
//                    try {
//                        String key = record.get(0) + "_" + record.get(2) + "_" + record.get(3).replace("\"", "").replace("\'", "").replace(" ", "");
//                        String replace = key.replace(" ", "");
//                        Activo va = ma.get(replace);
//                        if (va != null) {
//                            if (va.getType().equals("Bond")) {
//
//                                String daycount = record.get(16);
//                                if (daycount.contains("COMERCIALES")) {
//                                    va.setDayCount(Long.valueOf(1));
//                                } else if (daycount.contains("DESCONOCIDO")) {
//                                    va.setDayCount(Long.valueOf(2));
//                                } else if (daycount.contains("NATURALES")) {
//                                    va.setDayCount(Long.valueOf(3));
//                                } else {
//                                    va.setDayCount(null);
//                                }
//                                String mdate = record.get(37);
//                                try {
//                                    date = formatterTyC.parse(mdate);
//                                    cal.setTime(date);
//                                    va.setMaturityDate(new java.sql.Date(cal.getTimeInMillis()));
//                                } catch (Exception e) {
//                                }
//
//                                String amor = record.get(40).trim();
//                                switch (amor) {
//                                    case "1":
//                                        va.setAmortizing(Long.valueOf(1));
//                                        break;
//                                    case "0":
//                                        va.setAmortizing(Long.valueOf(0));
//                                        break;
//                                    default:
//                                        va.setAmortizing(null);
//                                        break;
//                                }
//                                String coll = deAccent(record.get(44).replaceAll("[^A-Za-z0-9]", "")).toLowerCase();
//                                Long lmbc = mapBondCollateral.get(coll);
//                                va.setBondCollateral(lmbc);
//
//                                String refrate = deAccent(record.get(82).replaceAll("[^A-Za-z0-9]", "")).toLowerCase();
//                                Long lmrr = mapReferenceRate.get(refrate);
//                                va.setReferenceRate(lmrr);
//
//                                String termSt = deAccent(record.get(92).replaceAll("[^A-Za-z0-9]", "")).toLowerCase();
//                                Long lmts = mapTermStructure.get(termSt);
//                                va.setTermStructure(lmts);
//                                // Perdoname madre por mi vida loca :v
//                                if (termSt.equals("curvacurvaesprealstripsconimpcerocupn")) {
//                                    va.setTermStructure(Long.valueOf(389));
//                                }
//                            } else {
//                            }
//                            ma.put(replace, va);
//                        } else {
//                            //System.out.println("Nulo para Ticker:"+replace );
//                        }
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("Malformado " + count);
//                    } catch (NullPointerException e) {
//                    }
//                }
//            } finally {
//                parser.close();
//                reader.close();
//            }
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            System.out.println("Excepcion de escape en CSV o una cosa rara que hace PIP" + ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return ma;
//    }

    public void persistSecurityClassFromCSV() {
        String r = "/home/neftaly/samasDocs/types.csv";
        Reader reader = null;
        try {
            final URL url = new URL("file://" + r);
            reader = new InputStreamReader(new BOMInputStream(url.openStream()), "UTF-8");
            final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL);
            try {
                int count = 0;
                for (final CSVRecord record : parser) {
                    count++;
                    try {
                        TipoValor sc = new TipoValor();
                        sc.setCode(record.get(0).replaceAll("\\s+", ""));
//                        sc.setKind(record.get(2));
                        sc.setDescription(record.get(1));
                        em.persist(sc);
                    } catch (NullPointerException e) {
                        System.out.println("Linea en blanco " + count);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Malformado " + count);
                    } catch (Exception e) {
                        System.out.println("Encontramos un Security Class con el mismo codigo");
                        e.printStackTrace();
                    }
                }
            } finally {
                parser.close();
                reader.close();
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CheckAssets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CheckAssets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CheckAssets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println("Excepcion de escape en CSV o una cosa rara que hace PIP");
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(CheckAssets.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
////
//    private boolean pushAssetsToDB(Map<String, Activo> assets) {
//        String query = "INSERT INTO ASSET( "
//                + " DTYPE,"
//                + " TICKER,"
//                + " NAME,"
//                + " TV,"
//                + " ISSUER,"
//                + " SERIES,"
//                + " CURRENCYDENOMINATION,"
//                + " AMORTIZING,"
//                + " MATURITYDATE,"
//                + " BONDCOLLATERAL_ID,"
//                + " DAYCOUNT_ID,"
//                + " REFERENCERATE_ID,"
//                + " TERMSTRUCTURE_ID"
//                + ") VALUES"
//                + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        MySQLConnection samas = new MySQLConnection("samas", "127.0.0.1", 3306, "samas", "test");
//        samas.setQuery(query);
//
//        for (Map.Entry<String, Activo> entry : assets.entrySet()) {
//            Activo assetValue = entry.getValue();
//            samas.insertAssetToBatch(assetValue);
//        }
//
//        return samas.executeBatch();
//    }
//
//    public void getAssetVectorFromCSV() {
//
//        assetType = getAssetType();
//
//        String query = "INSERT INTO ASSETVECTOR("
//                + " DTYPE,"
//                + " DATETIME,"
//                + " CLEANPRICE,"
//                + " ASSET_ID,"
//                + " AMOUNTOUTSTANDING,"
//                + " COUPONRATE,"
//                + " DIRTYPRICE,"
//                + " FACEVALUE,"
//                + " GRADEFITCH,"
//                + " GRADEHR,"
//                + " GRADEMOODYS,"
//                + " GRADESP,"
//                + " SPREAD,"
//                + " YIELD"
//                + ")VALUES "
//                + "(?, ?, ?, ?, ?, ?, ?, "
//                + "?, ?, ?, ?, ?, ?, ?)";
//        MySQLConnection push = new MySQLConnection("samas", "127.0.0.1", 3306, "samas", "test");
//        push.setQuery(query);
//        MySQLConnection samas = new MySQLConnection("samas", "127.0.0.1", 3306, "samas", "test");
//        ResultSet assets = samas.makeQuery("SELECT ID, TICKER FROM ASSET");
//        Map<Long, String> listAssetVector = new HashMap<>();
//
//        try {
//            while (assets.next()) {
//                listAssetVector.put(assets.getLong("ID"), assets.getString("TICKER"));
//            }
//            samas.closeQuery();
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        DocumentConnection dc = new DocumentConnection();
//        List<String> routes;
//        char delimiter = '\'';
//        routes = dc.listAllFiles();
//        for (String r : routes) {
//            System.out.print(r);
//            Reader reader = null;
//
//            String FECHA = r.replaceAll("[^0-9]", "");
//            try {
//                final URL url = new URL("file://" + r);
//                reader = new InputStreamReader(new BOMInputStream(url.openStream()), "UTF-8");
//                final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader().withIgnoreEmptyLines().withQuote(delimiter));
//                try {
//                    int count = 0;
//                    for (final CSVRecord record : parser) {
//                        count++;
//                        try {
//
//                            AssetVector value = new AssetVector();
//                            String key = record.get(1) + "_" + record.get(2) + "_" + record.get(3);
//                            for (Map.Entry<Long, String> entry : listAssetVector.entrySet()) {
//                                String ti = entry.getValue();
//                                if (ti.equals(key)) {
//                                    value.setAsset(entry.getKey());
//                                }
//                            }
//
//                            String tipovalor = record.get(1).replace("\"", "").replace("\'", "");
//                            for (Map.Entry<String, String> entry : assetType.entrySet()) {
//                                if (tipovalor.equals(entry.getKey())) {
//                                    value.setType(entry.getValue() + "Vector");
//                                }
//                            }
//
//                            try {
//                                //campo de fecha
//                                String asdf = FECHA + " 15:00:00";
//                                date = format.parse(asdf);
//                                cal.setTime(date);
//                            } catch (Exception e) {
//                                System.out.println("Excepcion para fecha: " + FECHA);
//
//                            }
//
//                            String cleanPrice = record.get(5);
//                            try {
//                                value.setCleanPrice(Double.valueOf(cleanPrice));
//                            } catch (Exception e) {
//                                value.setCleanPrice(Double.valueOf(0));
//                            }
//
//                            if (value.getType().equals("BondVector")) {
//
//                                String ao = record.get(12);
//                                try {
//                                    value.setAmountOutstanding(Double.valueOf(ao.trim()));
//                                } catch (Exception e) {
//                                    value.setAmountOutstanding(Double.valueOf(0));
//                                }
//
//                                String cr = record.get(7);
//                                try {
//                                    value.setCouponRate(Double.valueOf(cr));
//                                } catch (Exception e) {
//                                    value.setCouponRate(Double.valueOf(0));
//
//                                }
//
//                                String dp = record.get(4);
//                                try {
//                                    value.setDirtyPrice(Double.valueOf(dp));
//                                } catch (Exception e) {
//                                    value.setDirtyPrice(Double.valueOf(0));
//                                }
//
//                                String fv = record.get(52);
//                                try {
//                                    value.setFaceValue(Double.valueOf(fv));
//                                } catch (Exception e) {
//                                    value.setFaceValue(Double.valueOf(0));
//                                }
//
//                                value.setGradeFitch(record.get(53));
//                                value.setGradeHR(record.get(59));
//                                value.setGradeMoodys(record.get(36));
//                                value.setGradeSP(record.get(37));
//
//                                String sp = record.get(8);
//                                try {
//                                    value.setSpread(Double.valueOf(sp));
//                                } catch (Exception e) {
//                                    value.setSpread(Double.valueOf(0));
//                                }
//
//                                String yi = record.get(58);
//                                try {
//                                    value.setYield(Double.valueOf(yi));
//                                } catch (Exception e) {
//                                    value.setYield(Double.valueOf(0));
//                                }
//
//                            } else {
//
//                                value.setAmountOutstanding(null);
//                                value.setCouponRate(null);
//                                value.setDateTime(null);
//                                value.setDirtyPrice(null);
//                                value.setFaceValue(null);
//                                value.setGradeFitch(null);
//                                value.setGradeHR(null);
//                                value.setGradeMoodys(null);
//                                value.setGradeSP(null);
//                                value.setSpread(null);
//                                value.setYield(null);
//                            }
//
//                            push.insertAssetVectorToBatch(value, new java.sql.Timestamp(cal.getTimeInMillis()));
//                        } catch (NullPointerException e) {
//                        } catch (ArrayIndexOutOfBoundsException e) {
//                            e.printStackTrace();
//
//                        }
//                    }
//                } finally {
//                    parser.close();
//                    reader.close();
//                }
//            } catch (UnsupportedEncodingException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (MalformedURLException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            } finally {
//                if (push.executeBatch()) {
//                    System.out.println("....Yep");
//                } else {
//                    System.out.println("....Nope");
//                }
//            }
//        }
//        //NO, ENSERIO PERDONAME MADRE POR MI VIDA LOCA
//        try {
//            push.getPst().close();
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

}
