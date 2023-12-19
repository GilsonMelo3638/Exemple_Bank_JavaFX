package com.jmc.mazebankfx.Views;

import com.jmc.mazebankfx.Controllers.Admin.ClientCellController;
import com.jmc.mazebankfx.Models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

// Classe que define a fábrica de células para a exibição de clientes em uma lista
public class ClientCellFactory extends ListCell<Client> {
    // Método chamado sempre que uma célula precisa ser atualizada
    @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);

        // Verifica se a célula está vazia
        if (empty) {
            setText(null); // Limpa o texto da célula
            setGraphic(null); // Limpa o conteúdo gráfico da célula
        } else {
            // Se a célula não estiver vazia, carrega o conteúdo do arquivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ClientCell.fxml"));
            // Cria um controlador para a célula com o cliente correspondente
            ClientCellController controller = new ClientCellController(client);
            loader.setController(controller); // Define o controlador no carregador
            setText(null); // Limpa o texto da célula
            try {
                setGraphic(loader.load()); // Carrega o conteúdo gráfico da célula
            } catch (Exception e) {
                e.printStackTrace(); // Imprime a exceção se ocorrer um erro durante o carregamento
            }
        }
    }
}
