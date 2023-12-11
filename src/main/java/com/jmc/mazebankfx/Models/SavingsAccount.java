package com.jmc.mazebankfx.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

// Classe que representa uma conta poupança (Savings Account)
public class SavingsAccount extends Account {
    // Propriedade para o limite de saque da conta poupança
    private final DoubleProperty withdrawalLimit;

    // Construtor que inicializa as propriedades com os valores fornecidos
    public SavingsAccount(String owner, String accountNumber, double balance, double withdrawalLimit) {
        // Chama o construtor da classe pai (Account) para inicializar as propriedades comuns
        super(owner, accountNumber, balance);
        // Inicializa a propriedade específica da conta poupança
        this.withdrawalLimit = new SimpleDoubleProperty(this, "Withdrawal Limit", withdrawalLimit);
    }

    // Método getter para acessar a propriedade withdrawalLimit
    public DoubleProperty withdrawalLimitProp() {
        return withdrawalLimit;
    }

    // Sobrescreve o método toString para fornecer uma representação em string da conta poupança
    @Override
    public String toString() {
        return accountNumberProperty().get();
    }
}
