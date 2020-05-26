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
            model.addPlanet(planetsName.getText(), Integer.parseInt(radius.getText()),
                    Double.parseDouble(eccentricity.getText()));
            if (model.getNumberOfThisPlanet() < model.getPlanetCount()) {
                model.setNumberOfThisPlanet(model.getNumberOfThisPlanet() + 1);
                SI.setDataAboutPlanets();
            } else {
                DrawPlanet DP = new DrawPlanet();
                DP.drawPlanet(stage, model);
            }
        } else {
            Main.showMessage(stage.getScene().getWindow(), "Обязательно нужно указать название планеты, " +
                    "минимальное расстояние до солнца (число) и массу планеты (число)" );
        }
    }
}
