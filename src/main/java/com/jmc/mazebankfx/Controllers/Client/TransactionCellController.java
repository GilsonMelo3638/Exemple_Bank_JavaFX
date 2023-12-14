package com.jmc.mazebankfx.Controllers.Client;

import com.jmc.mazebankfx.Models.Model;
import com.jmc.mazebankfx.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    // Elementos do layout FXML
    public FontAwesomeIconView in_icon;      // Ícone de transação de entrada
    public FontAwesomeIconView out_icon;     // Ícone de transação de saída
    public Label trans_date_lbl;              // Rótulo de data da transação
    public Label sender_lbl;                  // Rótulo de remetente
    public Label receiver_lbl;                // Rótulo de destinatário
    public Button message_btn;                // Botão de mensagem
    public Label amount_lbl;                  // Rótulo de valor

    private final Transaction transaction;    // Transação associada a esta célula

    // Construtor que recebe uma transação para exibição
    public TransactionCellController(Transaction transaction) {
        this.transaction = transaction;
    }

    // Método chamado ao inicializar o controlador
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Vincula as propriedades da transação aos elementos do layout
        sender_lbl.textProperty().bind(transaction.senderProperty());
        receiver_lbl.textProperty().bind(transaction.receiverProperty());
        amount_lbl.textProperty().bind(transaction.amountProperty().asString());
        trans_date_lbl.textProperty().bind(transaction.dateProperty().asString());
        message_btn.setOnAction(event -> Model.getInstance().getViewFactory().showMessageWindow(transaction.senderProperty().get(), transaction.messageProperty().get()));

        // Configura os ícones com base na direção da transação
        transactionIcons();
    }

    // Configura os ícones com base na direção da transação (entrada ou saída)
    private void transactionIcons() {
        // Se o remetente for o cliente atual, configura ícones para saída
        if (transaction.senderProperty().get().equals(Model.getInstance().getClient().pAddressProperty().get())) {
            in_icon.setFill(Color.rgb(240, 240, 240));  // Cor de fundo para transação de entrada
            out_icon.setFill(Color.RED);                // Cor de fundo para transação de saída
        } else {
            in_icon.setFill(Color.GREEN);               // Cor de fundo para transação de entrada
            out_icon.setFill(Color.rgb(240, 240, 240)); // Cor de fundo para transação de saída
        }
    }
}
