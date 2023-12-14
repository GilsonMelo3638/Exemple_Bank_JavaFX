package com.jmc.mazebankfx.Views;

import com.jmc.mazebankfx.Controllers.Admin.AdminController;
import com.jmc.mazebankfx.Controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewFactory {
    // Tipo de conta usado para o login (CLIENT ou ADMIN)
    private AccountType loginAccountType;

    // Seletor de itens de menu para o cliente
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;

    // Vistas do cliente
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;

    // Seletor de itens de menu para o administrador
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;

    // Vistas do administrador
    private AnchorPane createClientView;
    private AnchorPane clientsView;
    private AnchorPane depositView;

    // Construtor
    public ViewFactory() {
        this.loginAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    // Métodos para obter e definir o tipo de conta de login
    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*
     * Seção de Vistas do Cliente
     * */
    // Obtém a propriedade do item de menu selecionado pelo cliente
    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    // Obtém a vista do painel de controle do cliente
    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    // Obtém a vista do painel de transações do cliente
    public AnchorPane getTransactionsView() {
        if (transactionsView == null) {
            try {
                transactionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    // Obtém a vista do painel de contas do cliente
    public AnchorPane getAccountsView() {
        if (accountsView == null) {
            try {
                accountsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accountsView;
    }

    // Exibe a janela principal do cliente
    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    /*
     * Seção de Vistas do Administrador
     * */
    // Obtém a propriedade do item de menu selecionado pelo administrador
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    // Obtém a vista do painel de criação de clientes do administrador
    public AnchorPane getCreateClientView() {
        if (createClientView == null) {
            try {
                createClientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateClient.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return createClientView;
    }

    // Obtém a vista do painel de visualização de clientes do administrador
    public AnchorPane getClientsView() {
        if (clientsView == null) {
            try {
                clientsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Clients.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientsView;
    }

    // Obtém a vista do painel de depósito do administrador
    public AnchorPane getDepositView() {
        if (depositView == null) {
            try {
                depositView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Deposit.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return depositView;
    }

    // Exibe a janela principal do administrador
    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);
    }

    // Exibe a janela de login
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    // Exibe uma janela de mensagem
    public void showMessageWindow(String pAddress, String messageText) {
        StackPane pane = new StackPane();
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        Label sender = new Label(pAddress);
        Label message = new Label(messageText);
        hBox.getChildren().addAll(sender, message);
        pane.getChildren().add(hBox);
        Scene scene = new Scene(pane, 300, 100);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();
    }

    // Cria e exibe uma nova janela com base no carregador FXML fornecido
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setResizable(false);
        stage.setTitle("Maze Bank");
        stage.show();
    }

    // Fecha a janela fornecida
    public void closeStage(Stage stage) {
        stage.close();
    }
}
