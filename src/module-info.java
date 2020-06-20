module planet {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    exports planetsystem.controllers;
    exports planetsystem.view;
    opens planetsystem.controllers;
    opens planetsystem.view;
}