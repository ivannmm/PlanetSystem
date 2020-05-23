package planetsystem.view;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import planetsystem.model.Model;

import java.util.Random;

public class DrawPlanet {

    public void drawPlanet (Stage stage, Model model) {
        stage.setWidth(1100);
        stage.setHeight(550);
        stage.setTitle("Моделирование системы: " + model.getSystemName());

        Color[] colors =
                {Color.AQUAMARINE, Color.BLUE, Color.CADETBLUE, Color.CORAL, Color.FIREBRICK,
        Color.HOTPINK, Color.MEDIUMSEAGREEN, Color.PLUM};

        Group root = new Group();
        Scene scene = new Scene(root);
        Circle sun = new Circle(stage.getWidth() / 2, stage.getHeight() / 2, 15, Color.YELLOW);

        Random random = new Random();
        Circle[] circles = new Circle[model.getPlanetCount()];

        for (int i = 1; i <= model.getPlanetCount(); i++) {
            circles[i - 1] = new Circle(5 + random.nextInt(5), colors[random.nextInt(7)]);
        }

        Image space = new Image("https://i.imgur.com/tNVA7AA.jpg");

        Canvas canvas = new Canvas(stage.getWidth(), stage.getHeight());
        root.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Button stopButton = new Button();
        stopButton.setText("Пауза");
        stopButton.setLayoutX(979);
        stopButton.setLayoutY(481);
        stopButton.setPrefWidth(100);

        root.getChildren().addAll(circles);
        scene.setFill(Color.AQUAMARINE);
        root.getChildren().addAll(sun, stopButton);

        new AnimationTimer() {
            boolean stopFlag = false;
            final double[] currentAngle = new double[model.getPlanetCount()];
            public void handle (long currentNanoTime) {
                for (int i = 1; i <= model.getPlanetCount(); i++) {
                    if (!stopFlag) {

                        /** model.dataBase.get(i).a / model.getMaxBigHalfShaft() * 240 - это относительный масштаб,
                         * чтобы планеты все были на экране, т.е.
                         * model.dataBase.get(i).a - абсолютная большая полуось
                         * model.dataBase.get(i).a / model.getMaxBigHalfShaft() * 240 - относительная большая полуось,
                         * которая дальше используется для вычисление относительной малой полуоси
                         */

                        int x = (int) (model.dataBase.get(i).bigHalfShaft / model.getMaxBigHalfShaft() * 240 * Math.sin(currentAngle[i - 1]) +
                                (stage.getWidth() / 2 - model.dataBase.get(i).focus));

                        /** координата y вычисляется как
                         * большая полуось * кв. корень из (1 - (эксцентриситет орбиты)^2) * текущий угол +
                         * + высота сцены / 2
                         */

                        int y = (int) ((model.dataBase.get(i).bigHalfShaft / model.getMaxBigHalfShaft() * 240) *
                                Math.sqrt(1 - Math.pow(model.dataBase.get(i).getEccentricity(), 2)) *
                                Math.cos(currentAngle[i - 1]) + (stage.getHeight() / 2));

                        currentAngle[i - 1] += 2 * Math.PI / (model.dataBase.get(i).getPeriod() * 60 /
                                model.getScaleTime());

                        if (currentAngle[i - 1] >= 2 * Math.PI)
                            currentAngle[i - 1] = 0;

                        circles[i - 1].setCenterX(x);
                        circles[i - 1].setCenterY(y);
                    }
                }

                stopButton.setOnAction(event -> {
                    if (!stopFlag)
                        stopButton.setText("Возобновить");
                    else stopButton.setText("Пауза");
                    stopFlag = !stopFlag;
                });
                gc.drawImage(space, 0, 0);
                stage.setScene(scene);

            }
        }.start();
    }
}
