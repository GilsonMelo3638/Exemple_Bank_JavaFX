package com.jmc.mazebankfx.Controllers.Admin;

import com.jmc.mazebankfx.Views.ClientCellFactory;
import com.jmc.mazebankfx.Models.Client;
import com.jmc.mazebankfx.Models.Model;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    // Campos de texto e botões no layout FXML
    public TextField pAddress_fld;        // Campo de texto para inserir o endereço do cliente
    public Button search_btn;              // Botão para buscar clientes
    public ListView<Client> result_listview; // ListView para exibir resultados da pesquisa
    public TextField amount_fld;           // Campo de texto para inserir o valor do depósito
    public Button deposit_btn;             // Botão para realizar o depósito

    private Client client;   // Cliente selecionado para depósito

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuração inicial quando o controlador é carregado
        search_btn.setOnAction(event -> onClientSearch());  // Configura o evento de busca ao clicar no botão
        deposit_btn.setOnAction(event -> onDeposit());      // Configura o evento de depósito ao clicar no botão
    }

    // Método chamado ao clicar no botão de busca de cliente
    private void onClientSearch() {
        // Obtém a lista de resultados da pesquisa
        ObservableList<Client> searchResults = Model.getInstance().searchClient(pAddress_fld.getText());
        result_listview.setItems(searchResults);           // Exibe os resultados na ListView
        result_listview.setCellFactory(e -> new ClientCellFactory());  // Configura a fábrica de células para exibir os clientes
        client = searchResults.get(0);                    // Seleciona o primeiro cliente da lista como cliente ativo
    }

    // Método chamado ao clicar no botão de depósito
    /**
     * Método para processar o depósito na conta poupança do cliente.
     */
    private void onDeposit() {
        try {
            // Obtém o valor do depósito do campo de texto
            double amount = Double.parseDouble(amount_fld.getText());

            // Calcula o novo saldo adicionando o valor do depósito ao saldo atual da conta poupança do cliente
            double newBalance = amount + client.savingsAccountProperty().get().balanceProperty().get();

            // Verifica se o campo de valor não está vazio antes de realizar o depósito
            if (!amount_fld.getText().isEmpty()) {
                // Realiza o depósito na conta poupança do cliente no banco de dados
                Model.getInstance().getDatabaseDriver().depositSavings(client.pAddressProperty().get(), newBalance);
            }

            emptyFields();  // Limpa os campos após o depósito
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Trata exceção se o valor inserido no campo não for um número válido
            // Pode ser adicionada uma lógica adicional para informar o usuário sobre o erro
        } catch (Exception e) {
            e.printStackTrace();
            // Trata exceções gerais, se houver, durante o processamento do depósito
            // Pode ser adicionada uma lógica adicional para informar o usuário sobre o erro
        }
    }


    // Método para limpar os campos de texto
    private void emptyFields() {
        pAddress_fld.setText("");
        amount_fld.setText("");
    }
}
