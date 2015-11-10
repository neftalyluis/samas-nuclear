/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.sessions.businesslogic.assetmgmt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.samas.ejb.entities.AssetType;
import mx.samas.ejb.entities.AssetVector;
import mx.samas.ejb.entities.Blotter;
import mx.samas.ejb.entities.Client;
import mx.samas.ejb.entities.PortfolioVector;
import mx.samas.ejb.entities.PositionVector;
import mx.samas.ejb.entities.RiskProfile;
import mx.samas.ejb.entities.SliceVector;
import mx.samas.ejb.entities.Strategy;
import mx.samas.ejb.entities.Transaction;

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
    public Boolean makePortfolio() {
        Boolean res = false;

        return res;
    }

    @Override
    public List<Strategy> getStrategyList() {
        Query q = em.createQuery("SELECT s FROM Strategy s");
        return q.getResultList();
    }

    @Override
    public List<RiskProfile> getRiskProfileList() {
        Query q = em.createQuery("SELECT rk FROM RiskProfile rk");
        return q.getResultList();
    }

    @Override
    public boolean createStrategy(Strategy s) {
        try {
            em.persist(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<AssetVector> getPricesBetweenDates(AssetType a) {

        //Obtengo mis Activos que voy a asignar a mis portafolios
        Query qa = em.createQuery("SELECT a FROM Asset a WHERE a.ticker= :tc");
        qa.setParameter("tc", "1A_TSLA_*");
        AssetType tesla = (AssetType) qa.getSingleResult();

        String dateInString = "2015-07-01 15:00:00 ";
        Date f = new Date();
        try {
            f = sdf.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(PortfolioManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        String las = "2015-07-14 15:00:00";
        Date l = new Date();
        try {
            l = sdf.parse(las);
        } catch (ParseException ex) {
            Logger.getLogger(PortfolioManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        Query q = em.createQuery("SELECT la FROM AssetVector la  WHERE la.asset= :asset AND la.dateTime BETWEEN :first AND :last ORDER BY la.dateTime ASC");

        q.setParameter("asset", a);
        q.setParameter("first", f);
        q.setParameter("last", l);

        return q.getResultList();
    }

    @Override
    public Boolean makeMyStrategy() {

        Boolean res = false;

        //Obtengo mis Activos que voy a asignar a mis portafolios
        Query q = em.createQuery("SELECT a FROM Asset a WHERE a.ticker= :tc");
        q.setParameter("tc", "1A_TSLA_*");
        AssetType tesla = (AssetType) q.getSingleResult();
        q.setParameter("tc", "1A_AMD_*");
        AssetType amd = (AssetType) q.getSingleResult();

        //Estrategia de cien mil a 50 50
        Strategy cienmil = new Strategy();
        cienmil.setName("Cien Mil");

        SliceVector tslacien = new SliceVector();
        tslacien.setAsset(tesla);
        tslacien.setStrategy(cienmil);
        tslacien.setTargetAllocation(50.0);

        SliceVector aemedecien = new SliceVector();
        aemedecien.setAsset(amd);
        aemedecien.setStrategy(cienmil);
        aemedecien.setTargetAllocation(50.0);

        List<SliceVector> lsvcien = new LinkedList<>();
        lsvcien.add(tslacien);
        lsvcien.add(aemedecien);
        cienmil.setSlices(lsvcien);

        //Estrategia de cinco millones a 20 80
        Strategy cincomill = new Strategy();
        cincomill.setName("Cinco Millones");

        SliceVector tslacinco = new SliceVector();
        tslacinco.setAsset(tesla);
        tslacinco.setStrategy(cincomill);
        tslacinco.setTargetAllocation(20.0);

        SliceVector aemedecinco = new SliceVector();
        aemedecinco.setAsset(amd);
        aemedecinco.setStrategy(cincomill);
        aemedecinco.setTargetAllocation(80.0);

        List<SliceVector> lsvcinco = new LinkedList<>();
        lsvcinco.add(tslacinco);
        lsvcinco.add(aemedecinco);
        cincomill.setSlices(lsvcinco);

        //Estrategia de un millon a 30 70
        Strategy unmill = new Strategy();
        unmill.setName("Un Millon");

        SliceVector tslamill = new SliceVector();
        tslamill.setAsset(tesla);
        tslamill.setStrategy(unmill);
        tslamill.setTargetAllocation(30.0);

        SliceVector aemedemill = new SliceVector();
        aemedemill.setAsset(amd);
        aemedemill.setStrategy(unmill);
        aemedemill.setTargetAllocation(70.0);

        List<SliceVector> lsvun = new LinkedList<>();
        lsvun.add(tslamill);
        lsvun.add(aemedemill);
        unmill.setSlices(lsvun);

        try {
            em.persist(tslacien);
            em.persist(aemedecien);
            em.persist(tslacinco);
            em.persist(aemedecinco);
            em.persist(tslamill);
            em.persist(aemedemill);

            em.persist(unmill);
            em.persist(cincomill);
            em.persist(cienmil);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        PortfolioVector pv1 = new PortfolioVector();
        pv1.setStrategy(cienmil);

        PortfolioVector pv2 = new PortfolioVector();
        pv2.setStrategy(unmill);

        PortfolioVector pv3 = new PortfolioVector();
        pv3.setStrategy(cincomill);

        try {
            em.persist(pv1);
            em.persist(pv2);
            em.persist(pv3);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public Boolean makeThreePortfoliosWithDiffMoney() {

        Boolean res = false;

        //Obtengo mis Activos que voy a asignar a mis portafolios
        Query q = em.createQuery("SELECT a FROM Asset a WHERE a.ticker= :tc");
        q.setParameter("tc", "1A_TSLA_*");
        AssetType tesla = (AssetType) q.getSingleResult();
        q.setParameter("tc", "1A_AMD_*");
        AssetType amd = (AssetType) q.getSingleResult();

        Strategy cienmil = new Strategy();
        cienmil.setName("TSLA & AMD");

        SliceVector tslacien = new SliceVector();
        tslacien.setAsset(tesla);
        tslacien.setStrategy(cienmil);
        tslacien.setTargetAllocation(72.5);

        SliceVector aemedecien = new SliceVector();
        aemedecien.setAsset(amd);
        aemedecien.setStrategy(cienmil);
        aemedecien.setTargetAllocation(27.5);

        List<SliceVector> lsvcien = new LinkedList<>();
        lsvcien.add(tslacien);
        lsvcien.add(aemedecien);
        cienmil.setSlices(lsvcien);

        try {
            em.persist(tslacien);
            em.persist(aemedecien);
            em.persist(cienmil);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        String dateInString = "2015-07-01 15:00:00 ";
        Date f = new Date();
        try {
            f = sdf.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(PortfolioManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        PortfolioVector pv1 = new PortfolioVector();
        pv1.setStrategy(cienmil);
        pv1.setDateTime(f);
        pv1.setComission(0.0);

        PortfolioVector pv2 = new PortfolioVector();
        pv2.setStrategy(cienmil);
        pv2.setDateTime(f);
        pv2.setComission(0.0);

        PortfolioVector pv3 = new PortfolioVector();
        pv3.setStrategy(cienmil);
        pv3.setDateTime(f);
        pv3.setComission(0.0);

        try {
            em.persist(pv1);
            em.persist(pv2);
            em.persist(pv3);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Transaction> lt = getAllTransactions();
        Transaction t = lt.get(5);

        //Deposito un chingo de dinero aqui
        Blotter deposito1 = new Blotter();
        Blotter deposito2 = new Blotter();
        Blotter deposito3 = new Blotter();

        deposito1.setPortfolio(pv1);
        deposito1.setCashFlow(1000000.0);
        deposito1.setInputDate(f);
        deposito1.setPrice(0.0);
        deposito1.setSettlementDate(f);
        deposito1.setTradeDate(f);
        deposito1.setTransaction(t);
        deposito1.setAsset(amd);

        deposito2.setPortfolio(pv2);
        deposito2.setCashFlow(200000.0);
        deposito2.setInputDate(f);
        deposito2.setPrice(0.0);
        deposito2.setSettlementDate(f);
        deposito2.setTradeDate(f);
        deposito2.setTransaction(t);

        deposito3.setPortfolio(pv3);
        deposito3.setCashFlow(48000000.0);
        deposito3.setInputDate(f);
        deposito3.setPrice(0.0);
        deposito3.setSettlementDate(f);
        deposito3.setTradeDate(f);
        deposito3.setTransaction(t);

        //Registro la transaccion
        try {
            em.persist(deposito1);
            em.persist(deposito2);
            em.persist(deposito3);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        q = em.createQuery("SELECT a FROM AssetVector a WHERE a.dateTime= :tc AND a.asset= :tsla");
        q.setParameter("tc", f);
        q.setParameter("tsla", tesla);
        AssetVector ptsla = (AssetVector) q.getSingleResult();

        q = em.createQuery("SELECT a FROM AssetVector a WHERE a.dateTime= :tc AND a.asset= :tsla");
        q.setParameter("tc", f);
        q.setParameter("tsla", amd);
        AssetVector pamd = (AssetVector) q.getSingleResult();

//        //Checo cuantos activos necesito con el presupuesto
////        List<AssetVector> ltsla = getPricesBetweenDates(tesla);
////        List<AssetVector> lamd = getPricesBetweenDates(amd);
//        List<SliceVector> lsvpv1 = pv1.getStrategy().getSlices();
//        
//        for (SliceVector sv : lsvpv1) {
//            Double howMuchAssets;
//            Long howManyAssets;
//            sv.getAsset()                            
//            if (sv.getAsset().equals(ptsla.getAsset())) {
//                howManyAssets=ptsla.getCleanPrice();
//            }
//            if (sv.getAsset().equals(pamd.getAsset())) {
//
//            }
//        }
        return res;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        Query q = em.createQuery("SELECT lt FROM Transaction lt ORDER BY lt.id ASC");
        return q.getResultList();
    }

    /**
     *
     * Creates a Strategy for a slicevector list, a name and a risk profile
     *
     * @param lsv The list containing the slice vectors.
     * @param name The name of the strategy
     * @param rk The Risk Profile for this strategy
     * @return
     */
    //Metodos basicos para crear un portafolio y hacerle un "bootstrap"
    @Override
    public Strategy createStrategy(List<SliceVector> lsv, String name, RiskProfile rk) {
        Strategy st = new Strategy();

        st.setName(name);
        st.setRiskProfile(rk);
        st.setSlices(lsv);

        try {
            for (SliceVector sv : lsv) {
                em.persist(sv);
            }
            em.persist(st);
        } catch (Exception e) {
            System.out.println("Error al persistir");
            e.getStackTrace();
        }

        return st;
    }

    @Override
    public PortfolioVector createPortfolioVector(Strategy s) {
        PortfolioVector pv = new PortfolioVector();

        pv.setStrategy(s);
        pv.setComission(null);
        pv.setComissionType(null);
        pv.setContract(null);
        pv.setDateTime(null);
        pv.setPortfolioStatus(null);

        try {
            em.persist(pv);
        } catch (Exception e) {
            System.out.println("Error al persistir");
            e.getStackTrace();
        }
        return pv;
    }

    @Override
    public Boolean depositMoneyToPortfolio(PortfolioVector pv, Double money) {

        Query q = em.createQuery("SELECT ts FROM Transaction ts WHERE ts.id= :tn");
        q.setParameter("tn", 5);
        Transaction t = (Transaction) q.getSingleResult();

        //Deposito un chingo de dinero aqui
        Blotter deposito1 = new Blotter();
        Date f = new Date();

        q = em.createQuery("SELECT ass FROM Asset ass WHERE ass.ticker= :ti");
        q.setParameter("ti", "MONEY");
        AssetType lqs = (AssetType) q.getSingleResult();

        deposito1.setPortfolio(pv);
        deposito1.setCashFlow(money);
        deposito1.setInputDate(f);
        deposito1.setPrice(0.0);
        deposito1.setSettlementDate(f);
        deposito1.setTradeDate(f);
        deposito1.setTransaction(t);
        deposito1.setAsset(lqs);

        PositionVector posv = new PositionVector();
        posv.setAsset(lqs);
        posv.setDateTime(f);
        posv.setPortfolioVector(pv);
        posv.setQuantity(Long.MIN_VALUE);
        posv.setSliceVector(null);

        try {
            em.persist(deposito1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean firstTimePortfolioPurchase(PortfolioVector pv) {

        Strategy st = pv.getStrategy();

        List<Transaction> lt = new LinkedList<>();

        List<SliceVector> lsv = pv.getStrategy().getSlices();
        for (SliceVector sv : lsv) {
            Query q = em.createQuery("SELECT av FROM AssetVector av WHERE av.asset= :ass ORDER BY av.dateTime DESC");
            AssetType a = sv.getAsset();
            q.setParameter("ass", a);
            AssetVector ave = (AssetVector) q.getSingleResult();
            Double precio = ave.getCleanPrice();
            Double diana = sv.getTargetAllocation();

        }

        return false;
    }

    @Override
    public void rebalance() {
        System.out.println("");
    }

    @Override
    public Boolean buyAsset(AssetType a, Long quantity, PortfolioVector pv) {

        Blotter v = new Blotter();
        Date d = new Date();

        Query q = em.createQuery("SELECT av FROM AssetVector av WHERE av.asset= :as ORDER BY av.dateTime DESC");
        AssetVector av = (AssetVector) q.getSingleResult();

        Query qu = em.createQuery("SELECT ts FROM Transaction ts WHERE ts.id= :tn");
        qu.setParameter("tn", 2);

        Transaction t = (Transaction) qu.getSingleResult();

        v.setAsset(a);
        v.setCashFlow(av.getCleanPrice() * (quantity));
        v.setPortfolio(pv);
        v.setActiveComission(0.0);
        v.setBroker(null);
        v.setContract(null);
        v.setInputDate(d);
        v.setPassiveComission(0.0);
        v.setPortfolio(pv);
        v.setPrice(av.getCleanPrice());
        v.setQuantity(-quantity);
        v.setQuantityFlow(0.0);
        v.setSettlementDate(d);
        v.setTradeDate(d);
        v.setTransaction(t);

        try {
            em.persist(v);
            return true;
        } catch (Exception e) {
            System.out.println("OOPS! I DID IT AGAIN :V");
            return false;
        }
    }

    @Override
    public Boolean sellAsset(AssetType a, Long quantity, PortfolioVector pv) {

        Blotter v = new Blotter();
        Date d = new Date();

        Query q = em.createQuery("SELECT av FROM AssetVector av WHERE av.asset= :as ORDER BY av.dateTime DESC");
        AssetVector av = (AssetVector) q.getSingleResult();

        Query qu = em.createQuery("SELECT ts FROM Transaction ts WHERE ts.id= :tn");
        qu.setParameter("tn", 1);

        Transaction t = (Transaction) qu.getSingleResult();

        v.setAsset(a);
        v.setCashFlow(av.getCleanPrice() * (-quantity));
        v.setPortfolio(pv);
        v.setActiveComission(0.0);
        v.setBroker(null);
        v.setContract(null);
        v.setInputDate(d);
        v.setPassiveComission(0.0);
        v.setPrice(av.getCleanPrice());
        v.setQuantity(quantity);
        v.setQuantityFlow(0.0);
        v.setSettlementDate(d);
        v.setTradeDate(d);
        v.setTransaction(t);

        try {
            em.persist(v);
            return true;
        } catch (Exception e) {
            System.out.println("OOPS! I DID IT AGAIN :V");
            return false;
        }
    }

    @Override
    public Boolean doEverything() {

        /*
         Nuevo Metodo para persistir SliceVector
         */
        SliceVector tesla = new SliceVector();
        SliceVector amd = new SliceVector();
        Date f = new Date();

        tesla.setDateTime(f);
        tesla.setTargetAllocation(30.0);

        amd.setDateTime(f);
        amd.setTargetAllocation(70.0);

        amd.setAsset(getAssetByTicker("1A_AMD_*"));
        tesla.setAsset(getAssetByTicker("1A_TSLA_*"));

        List<SliceVector> lsv = new LinkedList<>();
        lsv.add(amd);
        lsv.add(tesla);

        /*
         Termina metodo para persistir SliceVector
         */
        Strategy mio = createStrategy(lsv, "Lo Que Sea", null);

        PortfolioVector pv1 = createPortfolioVector(mio);
        PortfolioVector pv2 = createPortfolioVector(mio);

        depositMoneyToPortfolio(pv1, 50000000.0);
        depositMoneyToPortfolio(pv2, 10000000.0);

        try {

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public AssetType getAssetByTicker(String ticker) {
        Query qa = em.createQuery("SELECT a FROM Asset a WHERE a.ticker= :tc");
        qa.setParameter("tc", ticker);
        return (AssetType) qa.getSingleResult();
    }

    @Override
    public SliceVector createSliceVector() {
        SliceVector sv = new SliceVector();
        sv.setAsset(null);
        sv.setStrategy(null);
        sv.setTargetAllocation(Double.NaN);
        sv.setDateTime(null);
        try {
            em.persist(sv);
            return sv;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean setRevenue(Double flux, PortfolioVector pv) {
        Blotter v = new Blotter();
        Date d = new Date();

        Query qu = em.createQuery("SELECT ts FROM Transaction ts WHERE ts.id= :tn");
        qu.setParameter("tn", 12);
        Transaction t = (Transaction) qu.getSingleResult();

        v.setAsset(null);
        v.setCashFlow(flux);
        v.setPortfolio(pv);
        v.setActiveComission(0.0);
        v.setBroker(null);
        v.setContract(null);
        v.setInputDate(d);
        v.setPassiveComission(0.0);
        v.setPrice(flux);
        v.setQuantity(Long.valueOf(0));
        v.setQuantityFlow(0.0);
        v.setSettlementDate(d);
        v.setTradeDate(d);
        v.setTransaction(t);

        try {
            em.persist(v);
            return true;
        } catch (Exception e) {
            System.out.println("OOPS! I DID IT AGAIN :V");
            return false;
        }
    }

    @Override
    public Boolean createClient(Client c) {
        try {
            em.persist(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Client> getClients() {
        try {
            Query q = em.createQuery("SELECT c FROM Client c");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
