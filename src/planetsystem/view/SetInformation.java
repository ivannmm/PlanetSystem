package planetsystem.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import planetsystem.controllers.InfAboutPlanetsController;
import planetsystem.controllers.InfAboutSunController;
import planetsystem.model.Model;

import java.io.IOException;

public class SetInformation {

    Stage stage;
    Model model;

    public SetInformation(Stage stage, Model model) {
        this.stage = stage;
        this.model = model;
    }

    public void setDataAboutSun() throws IOException {

        stage.setTitle("Информация о звезде");

        Group root = new Group();
        Scene theScene = new Scene(root);
        Stop[] stops = new Stop[] { new Stop(0, Color.AQUAMARINE), new Stop(1, Color.LIGHTSTEELBLUE)};
        LinearGradient gradient = new LinearGradient(0, 450, 900, 0, false,
                CycleMethod.NO_CYCLE, stops);
        theScene.setFill(gradient);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/informationAboutSun.fxml"));
        root.getChildren().add(loader.load());

        InfAboutSunController controller = loader.getController();
        controller.setModel(model);
        controller.setClass(this);
        controller.setStage(stage);

        stage.setScene(theScene);
    }


    public void setDataAboutPlanets() throws Exception {
        Group root = new Group();
        Scene theScene = new Scene(root);

        Stop[] stops = new Stop[]{new Stop(0, Color.AQUAMARINE), new Stop(1, Color.LIGHTSTEELBLUE)};
        LinearGradient gradient = new LinearGradient(0, 450, 900, 0, false,
                CycleMethod.NO_CYCLE, stops);
        theScene.setFill(gradient);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/informationAboutPlanet.fxml"));
        root.getChildren().add(loader.load());
        InfAboutPlanetsController controller = loader.getController();

        controller.setClass(this);
        controller.setModel(model);
        controller.setStage(stage);

        stage.setTitle("Сбор информации: " + model.getNumberOfThisPlanet() + "/" + model.getPlanetCount());

        stage.setScene(theScene);
    }

}
