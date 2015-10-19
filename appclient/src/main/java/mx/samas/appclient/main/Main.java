package mx.samas.appclient.main;

import javax.ejb.AccessLocalException;
import javax.ejb.EJB;
import mx.samas.ejb.sessions.businesslogic.assetmgmt.PortfolioManagerRemote;
import mx.samas.ejb.sessions.system.UserManagerBean;
import mx.samas.ejb.sessions.system.UserManagerRemote;

/**
 *
 * @author neftaly
 */
public class Main {

    @EJB
    private static PortfolioManagerRemote pmr;

    @EJB
    private static UserManagerRemote umb;

//
//    public static void main(String[] args) {
//
//        System.out.println(pmr.makeMyStrategy());
//
////            List<AssetVector> lav = pmr.getPricesBetweenDates();
////            for (AssetVector av : lav) {
////                System.out.println(av.getCleanPrice()+" Fecha: "+av.getDateTime());
////
////            }
//    }
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/samas/appclient/fxml/NewStrategy.fxml"));
//        BorderPane pane = loader.load();
//        Scene scene = new Scene(pane, 650, 400);
//        primaryStage.setScene(scene);
//        AssetFinderController controller = ((AssetFinderController) loader.getController());
//        //bindSize(controller, scene);
//        //controller.setStage(primaryStage);
//        primaryStage.show();
//    }
//
////    private void bindSize(AssetFinderController controller, Scene scene) {
////        controller.timerSliderWidthProperty().bind(scene.widthProperty().subtract(500));
////        controller.mediaViewWidthProperty().bind(scene.widthProperty());
////        controller.mediaViewHeightProperty().bind(scene.heightProperty().subtract(70));
////    }
    public static void main(String[] args) {

        try {
            System.out.println(umb.greet());
        } catch (Exception e) {
            System.out.println("OOOPS!!!!!");
        }
    }
}
