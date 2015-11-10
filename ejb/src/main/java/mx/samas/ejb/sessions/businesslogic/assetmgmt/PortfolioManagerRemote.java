/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.sessions.businesslogic.assetmgmt;

import java.util.List;
import mx.samas.ejb.entities.AssetType;
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
public interface PortfolioManagerRemote {

    public boolean createStrategy(Strategy s);

    
    
    public List<RiskProfile> getRiskProfileList(); 

    public List<Strategy> getStrategyList();

    public Boolean doEverything();

    public Boolean makeMyStrategy();

    public Boolean makePortfolio();

    public List<AssetVector> getPricesBetweenDates(AssetType a);

    public Boolean makeThreePortfoliosWithDiffMoney();

    public List<Transaction> getAllTransactions();

    //Metodos basicos para crear un portafolio y hacerle un "bootstrap"
    public Strategy createStrategy(List<SliceVector> lsv, String name, RiskProfile rk);

    public PortfolioVector createPortfolioVector(Strategy s);

    public Boolean depositMoneyToPortfolio(PortfolioVector pv, Double money);

    public Boolean firstTimePortfolioPurchase(PortfolioVector pv);

    public AssetType getAssetByTicker(String ticker);

    //Metodos para hacer y que van despues del proceso anterior
    public void rebalance();

    public Boolean buyAsset(AssetType a, Long quantity, PortfolioVector pv);

    public Boolean sellAsset(AssetType a, Long quantity, PortfolioVector pv);

    public Boolean setRevenue(Double flux, PortfolioVector pv);

    public Boolean createClient(Client c);

    public List<Client> getClients();

}
