// Importação de classes necessárias do JavaFX e outras bibliotecas
package com.jmc.mazebankfx.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

// Classe que representa um cliente no sistema
public class Client {
    // Propriedades do cliente usando JavaFX Properties
    private final StringProperty firstName;       // Primeiro nome do cliente
    private final StringProperty lastName;        // Sobrenome do cliente
    private final StringProperty payeeAddress;    // Endereço do beneficiário (payee) do cliente
    private final ObjectProperty<Account> checkingAccount;  // Propriedade observável para a conta corrente do cliente
    private final ObjectProperty<Account> savingsAccount;   // Propriedade observável para a conta poupança do cliente
    private final ObjectProperty<LocalDate> dateCreated;    // Propriedade observável para a data de criação da conta do cliente

    // Construtor da classe que inicializa as propriedades com os valores fornecidos
    public Client(String fName, String lName, String pAddress, Account cAccount, Account sAccount, LocalDate date) {
        this.firstName = new SimpleStringProperty(this, "FirstName", fName);   // Inicialização da propriedade do primeiro nome
        this.lastName = new SimpleStringProperty(this, "LastName", lName);    // Inicialização da propriedade do sobrenome
        this.payeeAddress = new SimpleStringProperty(this, "Payee Address", pAddress);  // Inicialização da propriedade do endereço do beneficiário
        this.checkingAccount = new SimpleObjectProperty<>(this, "Checking Account", cAccount);  // Inicialização da propriedade da conta corrente
        this.savingsAccount = new SimpleObjectProperty<>(this, "Savings Account", sAccount);   // Inicialização da propriedade da conta poupança
        this.dateCreated = new SimpleObjectProperty<>(this, "Date", date);  // Inicialização da propriedade da data de criação
    }

    // Métodos getter para acessar as propriedades
    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty pAddressProperty() {
        return payeeAddress;
    }

    public ObjectProperty<Account> checkingAccountProperty() {
        return checkingAccount;
    }

    public ObjectProperty<Account> savingsAccountProperty() {
        return savingsAccount;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return dateCreated;
    }
}
