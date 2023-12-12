package com.jmc.mazebankfx.Controllers.Client;

import com.jmc.mazebankfx.Models.Model;
import com.jmc.mazebankfx.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe de controle para o menu do cliente, que lida com ações de botões e navegação.
 */
public class ClientMenuController implements Initializable {
    // Botões na interface do menu do cliente
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    /**
     * Inicializa o controlador, configurando ouvintes de eventos para as ações dos botões.
     *
     * @param url            A localização usada para resolver caminhos relativos para o objeto raiz.
     * @param resourceBundle Os recursos específicos para este controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adicionarOuvintes(); // Adiciona ouvintes de ação aos botões
    }

    /**
     * Adiciona ouvintes de eventos aos botões para lidar com as ações do usuário.
     */
    private void adicionarOuvintes() {
        dashboard_btn.setOnAction(evento -> aoClicarDashboard());
        transaction_btn.setOnAction(evento -> aoClicarTransacoes());
        accounts_btn.setOnAction(evento -> aoClicarContas());
        logout_btn.setOnAction(evento -> aoFazerLogout());
    }

    /**
     * Manipula o clique no botão do painel de controle atualizando o item de menu selecionado no modelo.
     */
    private void aoClicarDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    /**
     * Manipula o clique no botão de transações atualizando o item de menu selecionado no modelo.
     */
    private void aoClicarTransacoes() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    /**
     * Manipula o clique no botão de contas atualizando o item de menu selecionado no modelo.
     */
    private void aoClicarContas() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    /**
     * Manipula o clique no botão de logout fechando a janela do cliente, mostrando a janela de login e redefinindo o status de login.
     */
    private void aoFazerLogout() {
        // Obter a janela associada a qualquer botão (por exemplo, dashboard_btn)
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();

        // Fechar a janela do cliente
        Model.getInstance().getViewFactory().closeStage(stage);

        // Mostrar a janela de login
        Model.getInstance().getViewFactory().showLoginWindow();

        // Redefinir a bandeira de sucesso do login do cliente para falso
        Model.getInstance().setClientLoginSuccessFlag(false);
    }
}
