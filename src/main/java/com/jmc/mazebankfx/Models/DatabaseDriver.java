package com.jmc.mazebankfx.Models;

import java.sql.*;
import java.time.LocalDate;

// Classe que interage com o banco de dados SQLite para realizar operações relacionadas a clientes e administradores
public class DatabaseDriver {
    private Connection conn; // Objeto de conexão com o banco de dados

    // Construtor que estabelece a conexão com o banco de dados SQLite
    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:mazebank.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Seção Cliente
     * */

    // Método para obter os dados do cliente com base no endereço e senha
    public ResultSet getClientData(String pAddress, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='" + pAddress + "' AND Password='" + password + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Método para obter transações relacionadas a um cliente até um determinado limite
    public ResultSet getTransactions(String pAddress, int limit) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE Sender='" + pAddress + "' OR Receiver='" + pAddress + "' LIMIT " + limit + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Método para obter o saldo da conta poupança de um cliente
    public double getSavingsAccountBalance(String pAddress) {
        Statement statement;
        ResultSet resultSet;
        double balance = 0;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='" + pAddress + "';");
            balance = resultSet.getDouble("Balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    // Método para atualizar o saldo da conta poupança de um cliente com base em uma operação (adicionar ou subtrair)
    public void updateBalance(String pAddress, double amount, String operation) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='" + pAddress + "';");
            double newBalance;
            if (operation.equals("ADD")) {
                newBalance = resultSet.getDouble("Balance") + amount;
                statement.executeUpdate("UPDATE SavingsAccounts SET Balance=" + newBalance + " WHERE Owner='" + pAddress + "';");
            } else {
                if (resultSet.getDouble("Balance") >= amount) {
                    newBalance = resultSet.getDouble("Balance") - amount;
                    statement.executeUpdate("UPDATE SavingsAccounts SET Balance=" + newBalance + " WHERE Owner='" + pAddress + "';");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para criar uma nova transação e registrá-la no banco de dados
    public void newTransaction(String sender, String receiver, double amount, String message) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            LocalDate date = LocalDate.now();
            statement.executeUpdate("INSERT INTO " +
                    "Transactions(Sender, Receiver, Amount, Date, Message) " +
                    "VALUES ('" + sender + "', '" + receiver + "', " + amount + ", '" + date + "', '" + message + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Seção Admin
     * */

    // Método para obter os dados do administrador com base no nome de usuário e senha
    public ResultSet getAdminData(String username, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Admins WHERE Username='" + username + "' AND Password='" + password + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Método para criar um novo cliente e registrá-lo no banco de dados
    public void createClient(String fName, String lName, String pAddress, String password, LocalDate date) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "Clients (FirstName, LastName, PayeeAddress, Password, Date)" +
                    "VALUES ('" + fName + "', '" + lName + "', '" + pAddress + "', '" + password + "', '" + date.toString() + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para criar uma nova conta corrente e registrá-la no banco de dados
    public void createCheckingAccount(String owner, String number, double tLimit, double balance) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "CheckingAccounts (Owner, AccountNumber, TransactionLimit, Balance)" +
                    " VALUES ('" + owner + "', '" + number + "', " + tLimit + ", " + balance + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para criar uma nova conta poupança e registrá-la no banco de dados
    public void createSavingsAccount(String owner, String number, double wLimit, double balance) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "SavingsAccounts (Owner, AccountNumber, WithdrawalLimit, Balance)" +
                    " VALUES ('" + owner + "', '" + number + "', " + wLimit + ", " + balance + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter todos os dados de todos os clientes
    public ResultSet getAllClientsData() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Método para depositar em uma conta poupança
    public void depositSavings(String pAddress, double amount) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("UPDATE SavingsAccounts SET Balance=" + amount + " WHERE Owner='" + pAddress + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Métodos Utilitários
     * */

    // Método para pesquisar um cliente com base no endereço
    public ResultSet searchClient(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='" + pAddress + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Método para obter o último ID de clientes inserido no banco de dados
    public int getLastClientsId() {
        Statement statement;
        ResultSet resultSet;
        int id = 0;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sqlite_sequence WHERE name='Clients';");
            id = resultSet.getInt("seq");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    // Método para obter dados de uma conta corrente com base no endereço do cliente
    public ResultSet getCheckingAccountData(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAccounts WHERE Owner='" + pAddress + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Método para obter dados de uma conta poupança com base no endereço do cliente
    public ResultSet getSavingsAccountData(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='" + pAddress + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
