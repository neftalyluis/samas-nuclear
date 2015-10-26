/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.appclient.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mx.samas.appclient.main.Main;
import mx.samas.ejb.entities.AssetVector;
import mx.samas.ejb.entities.Client;

/**
 * FXML Controller class
 *
 * @author neftaly
 */
public class ClientesController implements Initializable {

    @FXML
    private Label state;

    @FXML
    private TextField nameClient;

    @FXML
    private PieChart piePort;

    @FXML
    private LineChart priceChart;

    @FXML
    private TableView clientsTable;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn nameColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//
//        ObservableList<Li.Data> data = FXCollections.observableArrayList(
//                new LineChart<>.Data("1A_TSLA_*", 27.5),
//                new LineChart<>.Data("1A_AMD_*", 72.5)
//        );
//
//        priceChart.setData(null
//        );
//
//        for (AssetVector av : Main.pmr.getPricesBetweenDates(Main.pmr.getAssetByTicker("1A_TSLA_*"))) {
//
//        }

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("1A_TSLA_*", 27.5),
                new PieChart.Data("1A_AMD_*", 72.5)
        );
        piePort.setData(data);
        piePort.setTitle("Strategy : TSLA & AMD");

        List<Client> lc = Main.pmr.getClients();
        for (Client c : lc) {
//         idColumn.set
        }
    }

    @FXML
    private void handleCreateClientAction(ActionEvent event) {
        // Button was clicked, do something...
        Client c = new Client();
        String name = nameClient.getText();
        if (!name.isEmpty()) {
            c.setName(name);
            try {
                Main.pmr.createClient(c);
                state.setText("Creado!");
            } catch (Exception e) {
                state.setText("Ocurrio un error");
            }
        } else {
            state.setText("El campo nombre esta vacio");
        }

    }

}
