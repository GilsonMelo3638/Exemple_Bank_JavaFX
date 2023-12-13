package com.jmc.mazebankfx.Controllers.Admin;

import com.jmc.mazebankfx.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    // Elementos visuais para exibição das propriedades do cliente
    public Label fName_lbl;      // Rótulo para o primeiro nome do cliente
    public Label lName_lbl;      // Rótulo para o sobrenome do cliente
    public Label pAddress_lbl;   // Rótulo para o endereço do cliente
    public Label ch_acc_lbl;     // Rótulo para o número da conta corrente do cliente
    public Label sv_acc_lbl;     // Rótulo para o número da conta poupança do cliente
    public Label date_lbl;       // Rótulo para a data de criação da conta do cliente
    public Button delete_btn;    // Botão para excluir o cliente

    // Cliente associado a esta célula do ListView
    private final Client client;

    // Construtor que recebe um objeto Cliente
    public ClientCellController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuração inicial quando o controlador é carregado
        // Associa as propriedades do cliente aos rótulos para exibição dinâmica
        fName_lbl.textProperty().bind(client.firstNameProperty());
        lName_lbl.textProperty().bind(client.lastNameProperty());
        pAddress_lbl.textProperty().bind(client.pAddressProperty());
        ch_acc_lbl.textProperty().bind(client.checkingAccountProperty().asString());
        sv_acc_lbl.textProperty().bind(client.savingsAccountProperty().asString());
        date_lbl.textProperty().bind(client.dateProperty().asString());
    }
}
