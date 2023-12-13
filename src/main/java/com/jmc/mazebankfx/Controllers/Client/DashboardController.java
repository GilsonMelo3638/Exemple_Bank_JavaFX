package com.jmc.mazebankfx.Controllers.Client;

import com.jmc.mazebankfx.Models.Model;
import com.jmc.mazebankfx.Models.Transaction;
import com.jmc.mazebankfx.Views.TransactionCellFactory;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    // Elementos do layout FXML
    public Text user_name;                // Texto de saudação ao usuário
    public Label login_date;              // Data de login
    public Label checking_bal;            // Saldo da conta corrente
    public Label checking_acc_num;         // Número da conta corrente
    public Label savings_bal;              // Saldo da conta poupança
    public Label savings_acc_num;          // Número da conta poupança
    public Label income_lbl;               // Rótulo de renda
    public Label expense_lbl;              // Rótulo de despesa
    public ListView transaction_listview; // ListView para exibir transações
    public TextField payee_fld;           // Campo de texto para destinatário
    public TextField amount_fld;           // Campo de texto para valor
    public TextArea message_fld;          // Área de texto para mensagem
    public Button send_money_btn;          // Botão para enviar dinheiro

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();  // Chama o método para vincular dados aos elementos do layout
        initLatestTransactionsList();  // Inicializa a lista de transações mais recentes
        // Define os itens da ListView com as transações mais recentes do modelo
        transaction_listview.setItems(Model.getInstance().getLatestTransactions());
        // Define a fábrica de células para a ListView usando a classe TransactionCellFactory
        transaction_listview.setCellFactory(e -> new TransactionCellFactory());
        send_money_btn.setOnAction(event -> onSendMoney());
    }

    // Método para vincular dados aos elementos do layout
    private void bindData() {
        // Vincula o texto de saudação ao usuário concatenando com o nome do cliente
        user_name.textProperty().bind(Bindings.concat("Hi, ").concat(Model.getInstance().getClient().firstNameProperty()));
        login_date.setText("Today, " + LocalDate.now());  // Define a data de login como a data atual
        // Vincula o saldo da conta corrente ao elemento de layout
        checking_bal.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().balanceProperty().asString());
        // Vincula o número da conta corrente ao elemento de layout
        checking_acc_num.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().accountNumberProperty());
        // Vincula o saldo da conta poupança ao elemento de layout
        savings_bal.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().balanceProperty().asString());
        // Vincula o número da conta poupança ao elemento de layout
        savings_acc_num.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().accountNumberProperty());
    }

    /**
     * Método para processar a transferência de dinheiro.
     */
    private void onSendMoney() {
        // Obter informações dos campos de entrada
        String receiver = payee_fld.getText();
        double amount = Double.parseDouble(amount_fld.getText());
        String message = message_fld.getText();
        String sender = Model.getInstance().getClient().pAddressProperty().get();

        // Buscar informações do destinatário no banco de dados
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchClient(receiver);
        try {
            // Verificar se o destinatário foi encontrado
            if (resultSet.isBeforeFirst()) {
                // Atualizar o saldo do destinatário no banco de dados (adicionar o valor transferido)
                Model.getInstance().getDatabaseDriver().updateBalance(receiver, amount, "ADD");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Subtrair o valor transferido da conta poupança do remetente no banco de dados
        Model.getInstance().getDatabaseDriver().updateBalance(sender, amount, "SUB");

        // Atualizar o saldo da conta poupança no objeto do cliente
        Model.getInstance().getClient().savingsAccountProperty().get().setBalance(
                Model.getInstance().getDatabaseDriver().getSavingsAccountBalance(sender)
        );

        // Registrar a nova transação no banco de dados
        Model.getInstance().getDatabaseDriver().newTransaction(sender, receiver, amount, message);

        // Limpar os campos de entrada após a conclusão da transferência
        payee_fld.setText("");
        amount_fld.setText("");
        message_fld.setText("");
    }


    // Método para inicializar a lista de transações mais recentes
    private void initLatestTransactionsList() {
        // Verifica se a lista de transações mais recentes está vazia
        if (Model.getInstance().getLatestTransactions().isEmpty()) {
            // Inicializa a lista de transações mais recentes no modelo
            Model.getInstance().setLatestTransactions();
        }
    }

    private void accountSummary() {
        double income = 0;
        double expenses = 0;
        if (Model.getInstance().getAllTransactions().isEmpty()) {
            Model.getInstance().setAllTransactions();
        }
        for (Transaction transaction : Model.getInstance().getAllTransactions()) {
            if (transaction.senderProperty().get().equals(Model.getInstance().getClient().pAddressProperty().get())) {
                expenses = expenses + transaction.amountProperty().get();
            } else {
                income = income + transaction.amountProperty().get();
            }
        }
        income_lbl.setText("+ $" + income);
        expense_lbl.setText("- $" + expenses);
    }

}
