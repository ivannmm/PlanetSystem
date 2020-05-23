package planetsystem.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import planetsystem.model.Model;

import java.io.IOException;

public class SetInformation {


    public void setDataAboutSun(Stage stage, Model model) throws IOException {

        Button continueButton = new Button();
        continueButton.setText("Продолжить");
        continueButton.setLayoutX(782);
        continueButton.setLayoutY(361);

        TextField sunName = new TextField();
        sunName.setLayoutX(289);
        sunName.setLayoutY(104);

        TextField mass = new TextField();
        mass.setLayoutX(289);
        mass.setLayoutY(149);

        stage.setTitle("Информация о звезде");

        Group root = new Group();
        Scene theScene = new Scene(root);
        Stop[] stops = new Stop[] { new Stop(0, Color.AQUAMARINE), new Stop(1, Color.LIGHTSTEELBLUE)};
        LinearGradient gradient = new LinearGradient(0, 450, 900, 0, false,
                CycleMethod.NO_CYCLE, stops);
        theScene.setFill(gradient);

        root.getChildren().add(FXMLLoader.load(getClass().getResource("../resources/informationAboutSun.fxml")));
        root.getChildren().add(continueButton);
        root.getChildren().add(sunName);
        root.getChildren().add(mass);

        continueButton.setOnAction(event -> {
                if (!sunName.getText().isEmpty() && Main.isNumber(mass.getText())) {
                    model.setSunMass(Double.parseDouble(mass.getText()));
                    model.setSunName(sunName.getText());
                    try {
                        setDataAboutPlanets(stage, model);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Main.showMessage(stage.getScene().getWindow(), "Обязательно нужно указать название звезды, " +
                            "и массу звезды (число)" );
                }
            }
        );
        stage.setScene(theScene);
    }

    private int i = 1;

    public void setDataAboutPlanets(Stage stage, Model model) throws Exception {
        Group root = new Group();
        Scene theScene = new Scene(root);

        Stop[] stops = new Stop[]{new Stop(0, Color.AQUAMARINE), new Stop(1, Color.LIGHTSTEELBLUE)};
        LinearGradient gradient = new LinearGradient(0, 450, 900, 0, false,
                CycleMethod.NO_CYCLE, stops);
        theScene.setFill(gradient);

        root.getChildren().add(FXMLLoader.load(getClass().getResource(
                "../resources/informationScene.fxml")));

        Button continueButton = new Button();
        continueButton.setText("Продолжить");
        continueButton.setLayoutX(782);
        continueButton.setLayoutY(361);

        TextField planetsName = new TextField();
        planetsName.setLayoutX(289);
        planetsName.setLayoutY(104);

        TextField radius = new TextField();
        radius.setLayoutX(289);
        radius.setLayoutY(149);

        TextField eccentricity = new TextField();
        eccentricity.setLayoutX(289);
        eccentricity.setLayoutY(192);

        root.getChildren().add(continueButton);
        root.getChildren().add(planetsName);
        root.getChildren().add(radius);
        root.getChildren().add(eccentricity);

        stage.setTitle("Сбор информации: " + i + "/" + model.getPlanetCount());

        continueButton.setOnAction(event -> {
            if (!planetsName.getText().isEmpty() && Main.isNumber(radius.getText())
                    && Main.isNumber(eccentricity.getText())) {
                    model.addPlanet(planetsName.getText(), Integer.parseInt(radius.getText()),
                            Double.parseDouble(eccentricity.getText()));
                if (i < model.getPlanetCount()) {
                        i++;
                        try {
                            setDataAboutPlanets(stage, model);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                } else {
                    DrawPlanet DP = new DrawPlanet();
                    DP.drawPlanet(stage, model);
                }
            } else {
                Main.showMessage(stage.getScene().getWindow(), "Обязательно нужно указать название планеты, " +
                        "минимальное расстояние до солнца (число) и массу планеты (число)" );
                }
        });
        stage.setScene(theScene);
    }

}
