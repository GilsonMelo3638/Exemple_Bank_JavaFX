package com.jmc.mazebankfx.Controllers.Admin;

import com.jmc.mazebankfx.Models.Model;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

// Classe do controlador para a interface do administrador
public class AdminController implements Initializable {

    // Referência para o contêiner BorderPane na interface do administrador
    public BorderPane admin_parent;

    // Método chamado durante a inicialização do controlador
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Adiciona um ouvinte para a propriedade 'adminSelectedMenuItem' no modelo
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            // Utiliza uma expressão switch para determinar o valor da propriedade
            switch (newVal) {
                // Se o valor for CLIENTS, exibe a visão de clientes
                case CLIENTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getClientsView());
                // Se o valor for DEPOSIT, exibe a visão de depósitos
                case DEPOSIT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getDepositView());
                // Se o valor não for nenhum dos anteriores, exibe a visão de criação de clientes
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateClientView());
            }
        });
    }
}
