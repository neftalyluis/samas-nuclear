/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.appclient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author neftaly
 */
public class RootController implements Initializable {

    @FXML
    private TreeView operationsView;

    @FXML
    private AnchorPane operationsPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> operationsItem = new TreeItem<>("Entidades");
        operationsItem.setExpanded(true);

        TreeItem<String> clientItem = new TreeItem<>("Clientes");
        clientItem.setExpanded(false);

        TreeItem<String> portfolioItem = new TreeItem<>("Portafolios");
        portfolioItem.setExpanded(false);

        TreeItem<String> superGeneratorItem = new TreeItem<>("SuperGenerador");
        portfolioItem.setExpanded(false);
        TreeItem<String> strategyItem = new TreeItem<>("Estrategias");
        portfolioItem.setExpanded(false);

        operationsView.setRoot(operationsItem);
        operationsItem.getChildren().add(clientItem);
        operationsItem.getChildren().add(portfolioItem);
        operationsItem.getChildren().add(superGeneratorItem);
        operationsItem.getChildren().add(strategyItem);

        operationsView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue,
                    Object newValue) {

                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                System.out.println(selectedItem.getValue());
                try {
                    AnchorPane menu = FXMLLoader.load(getClass().getResource("/mx/samas/appclient/fxml/" + selectedItem.getValue() + ".fxml"));
                    operationsPane.getChildren().setAll(menu);
                } catch (IOException | NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

}
