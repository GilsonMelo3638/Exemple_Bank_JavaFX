package com.jmc.mazebankfx.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Classe abstrata que serve como base para diferentes tipos de contas bancárias
public abstract class Account {

    // Propriedades que representam os dados da conta
    private final StringProperty owner; // Propriedade para o proprietário da conta
    private final StringProperty accountNumber; // Propriedade para o número da conta
    private final DoubleProperty balance; // Propriedade para o saldo da conta

    // Construtor que inicializa os dados da conta
    public Account(String owner, String accountNumber, double balance) {
        // Inicializa as propriedades com os valores fornecidos
        this.owner = new SimpleStringProperty(this, "Owner", owner);
        this.accountNumber = new SimpleStringProperty(this, "Account Number", accountNumber);
        this.balance = new SimpleDoubleProperty(this, "Balance", balance);
    }

    // Métodos para obter propriedades da conta
    public StringProperty ownerProperty() {
        return owner;
    }

    public StringProperty accountNumberProperty() {
        return accountNumber;
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

    // Método para definir o saldo da conta
    public void setBalance(double balance) {
        this.balance.set(balance);
    }
}
