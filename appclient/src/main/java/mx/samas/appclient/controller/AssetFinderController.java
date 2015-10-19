/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.appclient.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author neftaly
 */
public class AssetFinderController implements Initializable {

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

}
