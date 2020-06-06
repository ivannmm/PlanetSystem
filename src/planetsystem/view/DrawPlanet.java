package planetsystem.view;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.stage.Window;
import planetsystem.controllers.ButtonsOnDrawController;
import planetsystem.controllers.ShowInformationController;
import planetsystem.model.Model;
import planetsystem.model.Planet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static planetsystem.view.Main.showMessage;
import static planetsystem.view.ShowInformationAboutPlanet.show;

public class DrawPlanet {

    public void drawPlanet (Stage stage, Model model) throws IOException {

        stage.setWidth(1100);
        stage.setHeight(560);
        stage.setTitle("Моделирование системы: " + model.getSystemName());

        Color[] colors =
                {Color.AQUAMARINE, Color.BLUE, Color.CADETBLUE, Color.CORAL, Color.FIREBRICK,
        Color.HOTPINK, Color.MEDIUMSEAGREEN, Color.PLUM};

        Group root = new Group();
        Scene scene = new Scene(root);
        Circle sun = new Circle(stage.getWidth() / 2, stage.getHeight() / 2, 15, Color.YELLOW);

        Random random = new Random();
        Circle[] circles = new Circle[model.getPlanetCount()];
        Ellipse[] orbits = new Ellipse[model.getPlanetCount()];
        Double[] coefficient = new Double[model.getPlanetCount()];

        for (int i = 0; i < model.getPlanetCount(); i++) {

            int numberOfColor = random.nextInt(7);
            coefficient[i] = model.dataBase.get(i).bigHalfShaft / model.getMaxBigHalfShaft();
            circles[i] = new Circle(5 + random.nextInt(5), colors[numberOfColor]);

            int focus = (int) (coefficient[i] * 240 * model.dataBase.get(i).getEccentricity());

            double radiusX = (coefficient[i] * 240);

            double radiusY = (coefficient[i] * 240 * Math.sqrt(1 - Math.pow(model.dataBase.get(i).getEccentricity(), 2)));

            orbits[i] = new Ellipse(550 - focus, 275, radiusX, radiusY);
            orbits[i].setFill(Color.TRANSPARENT);
            orbits[i].setStroke(colors[numberOfColor]);
            orbits[i].setStrokeWidth(1.0);

        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/buttonsOnDraw.fxml"));
        root.getChildren().addAll(loader.load(), sun);
        ButtonsOnDrawController controller = loader.getController();
        controller.setModel(model);

        root.getChildren().addAll(circles);

        new AnimationTimer() {
            final double[] currentAngle = new double[model.getPlanetCount()];
            public void handle (long currentNanoTime) {
                switch (model.getShowOrbitsFlag()) {
                    case 1:
                        /** Удаление образов планет происходит для того, чтобы
                         * орбиты были под планетами, т.к. если они находятся поверх
                         * образов планет, то показ информации о планетах становится
                         * недоступным.
                         */
                        root.getChildren().removeAll(circles);
                        root.getChildren().addAll(orbits);
                        root.getChildren().addAll(circles);
                        model.setShowOrbitsFlag(0);
                        break;
                    case 2:
                        root.getChildren().removeAll(orbits);
                        model.setShowOrbitsFlag(0);
                        break;
                }

                for (int i = 0; i < model.getPlanetCount(); i++) {
                    final int forClick = i;
                    circles[i].setOnMouseClicked(mouseEvent -> {
                        try {
                            show(model.dataBase.get(forClick));
                        } catch (IOException ignored) {}
                    });

                    if (!model.getStopFlag()) {

                        currentAngle[i] += 2 * Math.PI / (model.dataBase.get(i).getPeriod() * 60 /
                                model.getScaleTime());

                        if (currentAngle[i] >= 2 * Math.PI)
                            currentAngle[i] = 0;

                        circles[i].setCenterX(model.getX(i, currentAngle[i], coefficient[i]));
                        circles[i].setCenterY(model.getY(i, currentAngle[i], coefficient[i]));
                    }
                }

                stage.setScene(scene);

            }
        }.start();
    }

    public void showInformation (Window window, Circle circle, Planet planet) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Информация о планете");
        alert.setGraphic(circle);
        alert.setHeaderText(planet.getName());
        alert.setContentText(planet.description[0] + '\n' + planet.description[1] + '\n' + planet.description[2]);
        alert.show();
    }
}
