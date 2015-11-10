/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.appclient.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import mx.samas.appclient.main.Main;
import mx.samas.ejb.entities.Client;
import mx.samas.ejb.entities.RiskProfile;
import mx.samas.ejb.entities.Strategy;

/**
 * FXML Controller class
 *
 * @author neftaly
 */
public class EstrategiasController implements Initializable {

    @FXML
    private ChoiceBox riskProfileChoice;

    @FXML
    private TextField nameStrategy;

    private Strategy c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        ObservableList<String> choices = FXCollections.observableArrayList();
//
//        List<RiskProfile> ls = Main.pmr.getRiskProfileList();
//        if (ls == null) {
//
//            for (RiskProfile s : ls) {
//                choices.add(s.getName());
//            }
//            riskProfileChoice.setItems(choices);
//        }
//
//        riskProfileChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String oldChoice, String newChoice) {
//                if (newChoice == null) {
//                } else {
//                }
//            }
//        });

    }

    @FXML
    private void handleCreateStrategyAction(ActionEvent event) {
        // Button was clicked, do something...
        String name = nameStrategy.getText();
        if (!name.isEmpty()) {
            c.setName(name);
            c.setRiskProfile(null);
            try {
                Main.pmr.createStrategy(c);
            } catch (Exception e) {
            }
        } else {
        }

    }

}
