package planetsystem.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import planetsystem.controllers.ShowInformationController;
import planetsystem.model.Model;
import planetsystem.model.Planet;
import java.io.IOException;

public class ShowInformationAboutPlanet {

    public static void show (Planet planet) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setWidth(400);
        stage.setHeight(300);

        Stop[] stops = new Stop[] { new Stop(0, Color.AQUAMARINE), new Stop(1, Color.LIGHTSTEELBLUE)};
        LinearGradient gradient = new LinearGradient(0, 400, 200, 0, false,
                CycleMethod.NO_CYCLE, stops);
        scene.setFill(gradient);

        FXMLLoader loader = new FXMLLoader(ShowInformationAboutPlanet.class.getResource("/show.fxml"));
        root.getChildren().add(loader.load());

        ShowInformationController controller = loader.getController();
        controller.setStage(stage);

        stage.getIcons().add(new Image("/forIcons.png"));
        stage.setTitle("Информация о планете");

        Label name = new Label(planet.getName());
        name.setLayoutX(20);
        name.setLayoutY(21);

        Label atmosphere = new Label(planet.getDescription()[0]);
        atmosphere.setLayoutX(20);
        atmosphere.setLayoutY(57);

        Label climate = new Label(planet.getDescription()[1]);
        climate.setLayoutX(20);
        climate.setLayoutY(91);

        Label conditions = new Label(planet.getDescription()[2]);
        conditions.setLayoutX(20);
        conditions.setLayoutY(127);

        Label period = new Label("~" + (int) (planet.getPeriod() / (3600 * 24)) + " дней");
        period.setLayoutX(20);
        period.setLayoutY(159);

        Label perigeli = new Label("~" + (int) planet.getPeriFocus() + " * 10 ^ 9 м");
        perigeli.setLayoutX(20);
        perigeli.setLayoutY(195);

        Label afeli = new Label("~" + (planet.bigHalfShaft + planet.focus) + " * 10 ^ 9 м");
        afeli.setLayoutX(20);
        afeli.setLayoutY(231);

        root.getChildren().addAll(name, atmosphere, climate, conditions, period, perigeli, afeli);

        stage.setScene(scene);
        stage.show();
    }
}
