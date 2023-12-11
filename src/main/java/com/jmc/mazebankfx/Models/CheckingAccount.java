package com.jmc.mazebankfx.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

// Classe que representa uma conta corrente (Checking Account)
public class CheckingAccount extends Account {
    // Propriedade para o limite de transações por dia
    private final IntegerProperty transactionLimit;

    // Construtor que inicializa as propriedades com os valores fornecidos
    public CheckingAccount(String owner, String accountNumber, double balance, int tLimit) {
        // Chama o construtor da classe pai (Account) para inicializar as propriedades comuns
        super(owner, accountNumber, balance);
        // Inicializa a propriedade específica da conta corrente
        this.transactionLimit = new SimpleIntegerProperty(this, "Transaction Limit", tLimit);
    }

    // Método getter para acessar a propriedade transactionLimit
    public IntegerProperty transactionLimitProp() {
        return transactionLimit;
    }

    // Sobrescreve o método toString para fornecer uma representação em string da conta corrente
    @Override
    public String toString() {
        return accountNumberProperty().get();
    }
}
