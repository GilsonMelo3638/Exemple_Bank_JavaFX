package com.jmc.mazebankfx.Controllers.Admin;

import com.jmc.mazebankfx.Models.Client;
import com.jmc.mazebankfx.Models.Model;
import com.jmc.mazebankfx.Views.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    // Referência para a ListView que exibirá a lista de clientes
    public ListView<Client> clients_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializa a lista de clientes e configura a fábrica de células para exibição personalizada
        initClientsList();
        clients_listview.setItems(Model.getInstance().getClients());
        clients_listview.setCellFactory(e -> new ClientCellFactory());
    }

    // Inicializa a lista de clientes se estiver vazia
    private void initClientsList() {
        // Verifica se a lista de clientes no modelo está vazia e a inicializa se necessário
        if (Model.getInstance().getClients().isEmpty()) {
            Model.getInstance().setClients();
        }
    }
}
