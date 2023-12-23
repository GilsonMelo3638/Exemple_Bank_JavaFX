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

/**
 * A classe ViewFactory é responsável por criar e gerenciar várias visualizações na aplicação Maze Bank.
 * Inclui métodos para exibir janelas de login, janelas de cliente e janelas de administrador, bem como obter
 * visualizações específicas para diferentes partes da aplicação.
 */
public class ViewFactory {

    /* Paineis de visualização: */
    private AnchorPane dashboardView; //controle do cliente.
    private AnchorPane transactionsView; //transações do cliente.
    private AnchorPane accountsView; //contas do cliente.
    private AnchorPane createClientView; //criação de clientes do administrador.
    private AnchorPane clientsView; //clientes do administrador.
    private AnchorPane depositView; //depósito do administrador.
    private AccountType loginAccountType; // Representa o tipo de conta utilizado para o login (CLIENT ou ADMIN).
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem; // Propriedade que mantém o item de menu selecionado pelo cliente(DASHBOARD, TRANSACTIONS, ACCOUNTS).
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem; //Propriedade que mantém o item de menu selecionado pelo administrador(CREATE_CLIENT, CLIENTS, DEPOSIT).


    /**
     * Construtor para a classe ViewFactory.
     * Inicializa o tipo de conta padrão como CLIENT e configura as propriedades para os itens de menu selecionados
     * tanto para clientes quanto para administradores.
     */
    public ViewFactory() {
        this.loginAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    /**
     * Obtém o tipo de conta de login atual.
     *
     * @return O tipo de conta de login atual (CLIENT ou ADMIN).
     */
    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    /**
     * Define o tipo de conta de login.
     *
     * @param loginAccountType O tipo de conta a ser definido (CLIENT ou ADMIN).
     */
    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /**
     * Obtém a propriedade que representa o item de menu selecionado para clientes.
     *
     * @return A propriedade para o item de menu de cliente selecionado.
     */
    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    /**
     * Obtém a propriedade que representa o item de menu selecionado para administradores.
     *
     * @return A propriedade para o item de menu de administração selecionado.
     */
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    /**
     * Exibe a janela de login carregando o arquivo FXML associado e criando um palco.
     */
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    /**
     * Exibe a janela principal do cliente carregando o arquivo FXML associado, criando um controlador de cliente
     * e criando um palco.
     */
    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    /**
     * Obtém a visualização do painel de controle do cliente.
     *
     * @return A visualização do painel de controle do cliente.
     */
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

    /**
     * Obtém a visualização do painel de transações do cliente.
     *
     * @return A visualização do painel de transações do cliente.
     */
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

    /**
     * Obtém a visualização do painel de contas do cliente.
     *
     * @return A visualização do painel de contas do cliente.
     */
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

    /**
     * Exibe a janela principal do administrador carregando o arquivo FXML associado, criando um controlador de administração
     * e criando um palco.
     */
    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);
    }

    /**
     * Obtém a visualização do painel de criação de cliente do administrador.
     *
     * @return A visualização do painel de criação de cliente do administrador.
     */
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

    /**
     * Obtém a visualização do painel de clientes do administrador.
     *
     * @return A visualização do painel de clientes do administrador.
     */
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

    /**
     * Obtém a visualização do painel de depósito do administrador.
     *
     * @return A visualização do painel de depósito do administrador.
     */
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

    /**
     * Exibe uma janela de mensagem.
     *
     * @param pAddress    O endereço do remetente da mensagem.
     * @param messageText O texto da mensagem.
     */
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
        stage.setTitle("Mensagem");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cria e exibe uma nova janela com base no carregador FXML fornecido.
     *
     * @param loader O carregador FXML associado à janela.
     */
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

    /**
     * Fecha o palco fornecido.
     *
     * @param stage O palco a ser fechado.
     */
    public void closeStage(Stage stage) {
        stage.close();
    }
}