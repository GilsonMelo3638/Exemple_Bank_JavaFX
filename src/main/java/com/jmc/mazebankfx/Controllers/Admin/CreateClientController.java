package com.jmc.mazebankfx.Controllers.Admin;

import com.jmc.mazebankfx.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Classe de controle para a criação de clientes pelo administrador.
 */
public class CreateClientController implements Initializable {
    // Campos de texto e controles na interface
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField password_fld;
    public CheckBox pAddress_box;
    public Label pAddress_lbl;
    public CheckBox ch_acc_box;
    public TextField ch_amount_fld;
    public CheckBox sv_acc_box;
    public TextField sv_amount_fld;
    public Button create_client_btn;
    public Label error_lbl;

    // Variáveis para armazenar informações temporárias
    private String payeeAddress;
    private boolean createCheckingAccountFlag = false;
    private boolean createSavingsAccountFlag = false;

    /**
     * Inicializa o controlador, configurando ouvintes de eventos para as ações dos elementos da interface.
     *
     * @param url            A localização usada para resolver caminhos relativos para o objeto raiz.
     * @param resourceBundle Os recursos específicos para este controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adicionarOuvintes(); // Adiciona ouvintes de ação aos elementos da interface
    }

    /**
     * Adiciona ouvintes de eventos aos elementos da interface para lidar com as ações do usuário.
     */
    /**
     * Método para adicionar ouvintes aos elementos de interface, respondendo a eventos específicos.
     */
    private void adicionarOuvintes() {
        // Ouvinte para o botão de criação de cliente
        create_client_btn.setOnAction(evento -> criarCliente());

        // Ouvinte para a caixa de seleção do endereço do beneficiário (payee)
        pAddress_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            // Se a caixa de seleção foi marcada
            if (newVal) {
                // Cria o endereço do beneficiário (payee)
                payeeAddress = criarPayeeAddress();
                // Executa ações específicas após a criação do endereço do beneficiário
                aoCriarPayeeAddress();
            }
        });

        // Ouvinte para a caixa de seleção da conta corrente
        ch_acc_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            // Se a caixa de seleção foi marcada, define a flag de criação de conta corrente como verdadeira
            if (newVal) {
                createCheckingAccountFlag = true;
            }
        });

        // Ouvinte para a caixa de seleção da conta poupança
        sv_acc_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            // Se a caixa de seleção foi marcada, define a flag de criação de conta poupança como verdadeira
            if (newVal) {
                createSavingsAccountFlag = true;
            }
        });
    }


    /**
     * Manipula o clique no botão de criar cliente.
     */
    private void criarCliente() {
        // Criar conta corrente
        if (createCheckingAccountFlag) {
            criarConta("Checking");
        }
        // Criar conta poupança
        if (createSavingsAccountFlag) {
            criarConta("Savings");
        }
        // Criar cliente
        String fName = fName_fld.getText();
        String lName = lName_fld.getText();
        String password = password_fld.getText();
        Model.getInstance().getDatabaseDriver().createClient(fName, lName, payeeAddress, password, LocalDate.now());
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        error_lbl.setText("Cliente criado com sucesso!");
        limparCampos();
    }

    /**
     * Criar conta corrente ou poupança com base no tipo fornecido.
     *
     * @param tipoConta O tipo de conta a ser criado ("Checking" ou "Savings").
     */
    private void criarConta(String tipoConta) {
        double saldo = Double.parseDouble(ch_amount_fld.getText());
        // Gerar número da conta
        String firstSection = "3201";
        String lastSection = Integer.toString((new Random()).nextInt(9999) + 1000);
        String numeroConta = firstSection + " " + lastSection;
        // Criar a conta corrente ou poupança
        if (tipoConta.equals("Checking")) {
            Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress, numeroConta, 10, saldo);
        } else {
            Model.getInstance().getDatabaseDriver().createSavingsAccount(payeeAddress, numeroConta, 2000, saldo);
        }
    }

    /**
     * Atualiza o rótulo do endereço do beneficiário ao criar um endereço.
     */
    private void aoCriarPayeeAddress() {
        if (fName_fld.getText() != null & lName_fld.getText() != null) {
            pAddress_lbl.setText(payeeAddress);
        }
    }

    /**
     * Cria um endereço do beneficiário com base no primeiro nome, sobrenome e ID do cliente.
     *
     * @return O endereço do beneficiário gerado.
     */
    private String criarPayeeAddress() {
        int id = Model.getInstance().getDatabaseDriver().getLastClientsId() + 1;
        char fChar = Character.toLowerCase(fName_fld.getText().charAt(0));
        return "@" + fChar + lName_fld.getText() + id;
    }

    /**
     * Limpa todos os campos da interface após a criação do cliente.
     */
    private void limparCampos() {
        fName_fld.setText("");
        lName_fld.setText("");
        password_fld.setText("");
        pAddress_box.setSelected(false);
        pAddress_lbl.setText("");
        ch_acc_box.setSelected(false);
        ch_amount_fld.setText("");
        sv_acc_box.setSelected(false);
        sv_amount_fld.setText("");
    }
}
