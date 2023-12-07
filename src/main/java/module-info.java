module com.jmc.mazebankfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.jmc.mazebankfx to javafx.fxml;
    exports com.jmc.mazebankfx;
    exports com.jmc.mazebankfx.Controllers;
    exports com.jmc.mazebankfx.Controllers.Admin;
    exports com.jmc.mazebankfx.Controllers.Client;
    exports com.jmc.mazebankfx.Models;
    exports com.jmc.mazebankfx.Views;
}