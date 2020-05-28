package planetsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import planetsystem.model.Model;
import planetsystem.view.DrawPlanet;
import planetsystem.view.Main;
import planetsystem.view.SetInformation;


public class InfAboutPlanetsController {

    @FXML
    public TextField planetsName;

    @FXML
    public TextField radius;

    @FXML
    public TextField eccentricity;

    Model model;

    SetInformation SI;

    Stage stage;

    public void setStage (Stage stage) {
        this.stage = stage;
    }

    public void setModel (Model model) {
        this.model = model;
    }

    public void setClass (SetInformation SI) {
        this.SI = SI;
    }

    public void nextScene() throws Exception {
        if (!planetsName.getText().isEmpty() && Main.isNumber(radius.getText())
                && Main.isNumber(eccentricity.getText())) {
            if (Double.parseDouble(eccentricity.getText()) < 1 && Double.parseDouble(eccentricity.getText()) >= 0 &&
                    Double.parseDouble(radius.getText()) > 0) {
                model.addPlanet(planetsName.getText(), Integer.parseInt(radius.getText()),
                        Double.parseDouble(eccentricity.getText()));
                if (model.getNumberOfThisPlanet() < model.getPlanetCount()) {
                    model.setNumberOfThisPlanet(model.getNumberOfThisPlanet() + 1);
                    SI.setDataAboutPlanets();
                } else {
                    DrawPlanet DP = new DrawPlanet();
                    DP.drawPlanet(stage, model);
                }
            } else Main.showMessage(stage.getScene().getWindow(), "Эксцентриситет орбиты должен быть не отрицательным числом " +
                    "и меньше единицы. Минимальное расстояние должно быть числом положительным");
        } else {
            Main.showMessage(stage.getScene().getWindow(), "Обязательно нужно указать название планеты, " +
                    "минимальное расстояние до солнца (число) и эксцентриситет орбиты планеты (от 0 до 1 (не включительно))" );
        }
    }
}
