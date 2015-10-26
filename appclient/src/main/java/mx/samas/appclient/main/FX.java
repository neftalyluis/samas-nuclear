/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.appclient.main;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.ejb.EJB;
import mx.samas.ejb.sessions.businesslogic.assetmgmt.PortfolioManagerRemote;
import mx.samas.ejb.sessions.system.UserManagerRemote;

/**
 *
 * @author neftaly
 */
public class FX extends Application{



//    public static void main(String[] args) {
//
//        try {
//            pmr.rebalance();
//            System.out.println(umb.greet());
//        } catch (Exception e) {
//            System.out.println("OOOPS!!!!!");
//        }
//    }
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SAMAS");

        initRootLayout();

        //showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/mx/samas/appclient/fxml/Root.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * Shows the person overview inside the root layout.
//     */
//    public void showPortfolioView() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(Main.class.getResource("view/PersonOverview.fxml"));
//            AnchorPane personOverview = (AnchorPane) loader.load();
//
//            // Set person overview into the center of root layout.
//            rootLayout.set(personOverview);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
