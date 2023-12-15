package com.jmc.mazebankfx.Controllers;

import com.jmc.mazebankfx.Models.Model;
import com.jmc.mazebankfx.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para a interface de login.
 */
public class LoginController implements Initializable {

    // Elementos da interface
    public ChoiceBox<AccountType> acc_selector;     // Caixa de seleção para o tipo de conta (CLIENT ou ADMIN)
    public Label payee_address_lbl;                 // Rótulo para o endereço do beneficiário ou nome de usuário
    public TextField payee_address_fld;             // Campo de texto para o endereço do beneficiário ou nome de usuário
    public TextField password_fld;                  // Campo de texto para a senha
    public Button login_btn;                        // Botão de login
    public Label error_lbl;                         // Rótulo para mensagens de erro

    // Método chamado durante a inicialização da interface
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configura o ChoiceBox para selecionar o tipo de conta (CLIENT ou ADMIN)
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());

        // Adiciona um ouvinte para alterações na seleção do tipo de conta
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());

        // Adiciona um ouvinte para o evento de clique no botão de login
        login_btn.setOnAction(event -> onLogin());
    }

    // Método chamado quando o botão de login é pressionado
    private void onLogin() {
        // Obtém a referência à janela atual
        Stage stage = (Stage) error_lbl.getScene().getWindow();

        // Verifica se é uma conta de cliente
        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT) {
            // Avalia as credenciais de login do cliente
            Model.getInstance().evaluateClientCred(payee_address_fld.getText(), password_fld.getText());

            // Se as credenciais de login forem bem-sucedidas, exibe a janela do cliente e fecha a janela de login
            if (Model.getInstance().getClientLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showClientWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                // Se as credenciais de login falharem, limpa os campos e exibe uma mensagem de erro
                payee_address_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("Credenciais de login incorretas.");
            }
        } else {
            // Avalia as credenciais de login do administrador
            Model.getInstance().evaluateAdminCred(payee_address_fld.getText(), password_fld.getText());

            // Se as credenciais de login forem bem-sucedidas, exibe a janela do administrador e fecha a janela de login
            if (Model.getInstance().getAdminLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                // Se as credenciais de login falharem, limpa os campos e exibe uma mensagem de erro
                payee_address_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("Credenciais de login incorretas");
            }
        }
    }

    // Método chamado quando o tipo de conta é alterado no ChoiceBox
    private void setAcc_selector() {
        // Atualiza o tipo de conta no modelo
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());

        // Altera o rótulo de endereço do beneficiário de acordo com o tipo de conta selecionado
        if (acc_selector.getValue() == AccountType.ADMIN) {
            payee_address_lbl.setText("Nome de Usuário:");
        } else {
            payee_address_lbl.setText("Endereço do Beneficiário:");
        }
    }
}
