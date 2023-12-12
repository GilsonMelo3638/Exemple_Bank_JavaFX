package com.jmc.mazebankfx.Controllers.Admin;

import com.jmc.mazebankfx.Models.Model;
import com.jmc.mazebankfx.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe de controle para o menu de administração, que lida com ações de botões e navegação.
 */
public class AdminMenuController implements Initializable {
    // Botões na interface do menu de administração
    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;

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
        create_client_btn.setOnAction(evento -> aoCriarCliente());
        clients_btn.setOnAction(evento -> aoVerClientes());
        deposit_btn.setOnAction(evento -> aoDepositar());
        logout_btn.setOnAction(evento -> aoFazerLogout());
    }

    /**
     * Manipula o clique no botão de criar cliente atualizando o item de menu selecionado no modelo.
     */
    private void aoCriarCliente() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_CLIENT);
    }

    /**
     * Manipula o clique no botão de clientes atualizando o item de menu selecionado no modelo.
     */
    private void aoVerClientes() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
    }

    /**
     * Manipula o clique no botão de depósito atualizando o item de menu selecionado no modelo.
     */
    private void aoDepositar() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DEPOSIT);
    }

    /**
     * Manipula o clique no botão de logout fechando a janela de administração, mostrando a janela de login e redefinindo o status de login.
     */
    private void aoFazerLogout() {
        // Obter a janela associada a qualquer botão (por exemplo, clients_btn)
        Stage stage = (Stage) clients_btn.getScene().getWindow();

        // Fechar a janela de administração
        Model.getInstance().getViewFactory().closeStage(stage);

        // Mostrar a janela de login
        Model.getInstance().getViewFactory().showLoginWindow();

        // Redefinir a bandeira de sucesso do login da administração para falso
        Model.getInstance().setAdminLoginSuccessFlag(false);
    }
}
