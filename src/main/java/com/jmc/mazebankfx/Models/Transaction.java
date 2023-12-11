package com.jmc.mazebankfx.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

// Classe que representa uma transação
public class Transaction {
    // Propriedades da transação usando JavaFX Properties
    private final StringProperty sender;
    private final StringProperty receiver;
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty message;

    // Construtor da classe que inicializa as propriedades com os valores fornecidos
    public Transaction(String sender, String receiver, double amount, LocalDate date, String message) {
        this.sender = new SimpleStringProperty(this, "sender", sender);
        this.receiver = new SimpleStringProperty(this, "Receiver", receiver);
        this.amount = new SimpleDoubleProperty(this, "Amount", amount);
        this.date = new SimpleObjectProperty<>(this, "Date", date);
        this.message = new SimpleStringProperty(this, "Message", message);
    }

    // Métodos getter para acessar as propriedades
    public StringProperty senderProperty() {
        return this.sender;
    }

    public StringProperty receiverProperty() {
        return this.receiver;
    }

    public DoubleProperty amountProperty() {
        return this.amount;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return this.date;
    }

    public StringProperty messageProperty() {
        return this.message;
    }
}
