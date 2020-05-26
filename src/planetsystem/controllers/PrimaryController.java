package planetsystem.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import planetsystem.model.Model;
import planetsystem.view.SetInformation;

import java.io.IOException;

import static planetsystem.view.Main.isNumber;
import static planetsystem.view.Main.showMessage;

public class PrimaryController {

    @FXML
    public TextField systemName;
    @FXML
    public TextField planetCount;
    @FXML
    public ChoiceBox<String> scaleTime = new ChoiceBox<>();

    @FXML
    void initialize() {
        scaleTime.getItems().addAll("1 сек.", "1 мин.", "1 час", "1 день", "1 неделя", "1 месяц",
                "1 год", "10 лет");
    }

    Model model;
    Stage stage;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void nextScene() throws IOException {
        if (isNumber(planetCount.getText()) && !systemName.getText().isEmpty()
                && scaleTime.getValue() != null) {
            SetInformation IAS = new SetInformation(stage, model);
            model.setPlanetCount(Integer.parseInt(planetCount.getText()));
            model.setSystemName(systemName.getText());
            model.setScaleTime(scaleTime.getValue());
            IAS.setDataAboutSun();
        }
        else {
            showMessage(stage.getScene().getWindow(),
                    "Обязательно нужно указать количество планет (цифрой или числом), " +
                            "название планетарной системы и выбрать масштаб времени");
        }
    }
}
