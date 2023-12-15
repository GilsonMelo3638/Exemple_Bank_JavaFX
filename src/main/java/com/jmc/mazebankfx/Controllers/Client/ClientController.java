package com.jmc.mazebankfx.Controllers.Client;

import com.jmc.mazebankfx.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller responsável pela lógica da interface do cliente.
 */
public class ClientController implements Initializable {
    // Referência para o painel principal da interface do cliente
    public BorderPane client_parent;

    /**
     * Método chamado durante a inicialização da interface do cliente.
     * @param url URL de localização do objeto.
     * @param resourceBundle Recurso específico para localização.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Adiciona um ouvinte para a mudança do item de menu selecionado no cliente
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            // Utiliza um switch para determinar a ação com base no novo item de menu selecionado
            switch (newVal) {
                case TRANSACTIONS ->
                    // Define o centro do painel principal para exibir a visualização de transações
                        client_parent.setCenter(Model.getInstance().getViewFactory().getTransactionsView());
                case ACCOUNTS ->
                    // Define o centro do painel principal para exibir a visualização de contas
                        client_parent.setCenter(Model.getInstance().getViewFactory().getAccountsView());
                default ->
                    // Define o centro do painel principal para exibir a visualização do painel principal (dashboard)
                        client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}

