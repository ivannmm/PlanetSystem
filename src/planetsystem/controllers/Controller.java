package planetsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class Controller {
    @FXML
    public ChoiceBox<String> scaleTime = new ChoiceBox<>();
    @FXML
    void initialize() {
        scaleTime.getItems().addAll("1 сек.", "1 мин.", "1 час", "1 день", "1 неделя", "1 месяц",
                "1 год", "10 лет");
    }
}
