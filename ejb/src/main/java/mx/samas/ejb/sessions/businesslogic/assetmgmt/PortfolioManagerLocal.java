/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.sessions.businesslogic.assetmgmt;

import java.util.List;
import mx.samas.ejb.entities.Asset;
import mx.samas.ejb.entities.AssetVector;
import mx.samas.ejb.entities.Client;
import mx.samas.ejb.entities.PortfolioVector;
import mx.samas.ejb.entities.RiskProfile;
import mx.samas.ejb.entities.SliceVector;
import mx.samas.ejb.entities.Strategy;
import mx.samas.ejb.entities.Transaction;

/**
 *
 * @author neftaly
 */
public interface PortfolioManagerLocal {

    public String test(String a);

    boolean createStrategy(Strategy s);

    List<Strategy> getStrategyList();

    List<RiskProfile> getRiskProfileList();

    Boolean doEverything();

    Boolean makeMyStrategy();

    Boolean makePortfolio();

    List<AssetVector> getPricesBetweenDates(Asset a);

    Boolean makeThreePortfoliosWithDiffMoney();

    List<Transaction> getAllTransactions();

    //Metodos basicos para crear un portafolio y hacerle un "bootstrap"
    Strategy createStrategy(List<SliceVector> lsv, String name, RiskProfile rk);

    PortfolioVector createPortfolioVector(Strategy s);

    Boolean depositMoneyToPortfolio(PortfolioVector pv, Double money);

    Boolean firstTimePortfolioPurchase(PortfolioVector pv);

    Asset getAssetByTicker(String ticker);

    //Metodos para hacer y que van despues del proceso anterior
    void rebalance();

    Boolean buyAsset(Asset a, Long quantity, PortfolioVector pv);

    Boolean sellAsset(Asset a, Long quantity, PortfolioVector pv);

    SliceVector createSliceVector();

    Boolean setRevenue(Double flux, PortfolioVector pv);

    Boolean createClient(Client c);

    List<Client> getClients();

}
