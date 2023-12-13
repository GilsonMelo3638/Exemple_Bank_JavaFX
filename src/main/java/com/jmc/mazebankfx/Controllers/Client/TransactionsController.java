package com.jmc.mazebankfx.Controllers.Client;

import com.jmc.mazebankfx.Models.Model;
import com.jmc.mazebankfx.Models.Transaction;
import com.jmc.mazebankfx.Views.TransactionCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    // ListView que exibirá todas as transações
    public ListView<Transaction> transactions_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializa a lista de todas as transações
        initAllTransactionsList();

        // Define os itens da ListView com todas as transações do modelo
        transactions_listview.setItems(Model.getInstance().getAllTransactions());

        // Define a fábrica de células para a ListView usando a classe TransactionCellFactory
        transactions_listview.setCellFactory(e -> new TransactionCellFactory());
    }

    // Método para inicializar a lista de todas as transações
    private void initAllTransactionsList() {
        // Verifica se a lista de todas as transações está vazia
        if (Model.getInstance().getAllTransactions().isEmpty()) {
            // Inicializa a lista de todas as transações no modelo
            Model.getInstance().setAllTransactions();
        }
    }
}
