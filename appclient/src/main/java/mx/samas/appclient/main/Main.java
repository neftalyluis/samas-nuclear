package mx.samas.appclient.main;

import javax.ejb.EJB;
import mx.samas.ejb.sessions.businesslogic.assetmgmt.PortfolioManagerRemote;
import mx.samas.ejb.sessions.system.UserManagerRemote;

/**
 *
 * @author neftaly
 */
public class Main {
    
    @EJB
    public static PortfolioManagerRemote pmr;

    @EJB
    public static UserManagerRemote umb;

    
    public static void main(String[] args) {
        FX.main(args);
    }
}
