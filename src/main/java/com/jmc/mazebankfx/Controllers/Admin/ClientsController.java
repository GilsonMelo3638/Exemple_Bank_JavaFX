package com.jmc.mazebankfx.Controllers.Admin;

import com.jmc.mazebankfx.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    public ListView<Client> clients_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  initClientsList();
     //   clients_listview.setItems(Model.getInstance().getClients());
    //    clients_listview.setCellFactory(e -> new ClientCellFactory());
    }


}