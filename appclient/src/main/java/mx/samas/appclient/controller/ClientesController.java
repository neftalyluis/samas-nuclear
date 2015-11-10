/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.appclient.controller;

import com.panemu.tiwulfx.table.TableControl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mx.samas.appclient.main.Main;
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
    private TableControl clientTable;

    @FXML
    private Tab readTab;

    private ObservableList clientData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readTab.setOnSelectionChanged(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                if (readTab.isSelected()) {

                }
            }
        });

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

    private ObservableList getInitialData() {
        return FXCollections.observableList(Main.pmr.getClients());
    }
}
