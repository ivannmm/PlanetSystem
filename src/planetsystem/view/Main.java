package planetsystem.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.Window;
import planetsystem.model.Model;

import java.io.IOException;


public class Main extends Application {

    Model model = new Model();



    @Override
    public void start(Stage primaryStage) throws Exception{

        Button continueButton = new Button();
        continueButton.setText("Продолжить");
        continueButton.setLayoutX(782);
        continueButton.setLayoutY(361);

        TextField planetCount = new TextField();
        planetCount.setLayoutX(239);
        planetCount.setLayoutY(120);

        TextField systemName = new TextField();
        systemName.setLayoutX(239);
        systemName.setLayoutY(86);

        ChoiceBox<String> scaleTime = new ChoiceBox<>();
        scaleTime.getItems().addAll("1 сек.", "1 мин.", "1 час", "1 день", "1 неделя", "1 месяц",
                "1 год", "10 лет");
        scaleTime.setLayoutX(239.0);
        scaleTime.setLayoutY(156.0);
        scaleTime.setPrefWidth(150.0);

        primaryStage.setTitle("Planet System");
        primaryStage.setWidth(900);
        primaryStage.setHeight(450);
        primaryStage.getIcons().add(new Image("https://pngimg.com/uploads/earth/earth_PNG10.png"));

        Group root = new Group();
        Scene theScene = new Scene(root);
        Stop[] stops = new Stop[] { new Stop(0, Color.AQUAMARINE), new Stop(1, Color.LIGHTSTEELBLUE)};
        LinearGradient gradient = new LinearGradient(0, 450, 900, 0, false,
                CycleMethod.NO_CYCLE, stops);
        theScene.setFill(gradient);


        root.getChildren().addAll(FXMLLoader.load(getClass().getResource("../resources/sample.fxml")),
                continueButton, planetCount, systemName, scaleTime);

        primaryStage.setScene(theScene);
        primaryStage.show();

        continueButton.setOnAction(event -> {
            if (isNumber(planetCount.getText()) && !systemName.getText().isEmpty()
                && scaleTime.getValue() != null) {
                    SetInformation IAS = new SetInformation();
                    model.setPlanetCount(Integer.parseInt(planetCount.getText()));
                    model.setSystemName(systemName.getText());
                    model.setScaleTime(scaleTime.getValue());
                    try {
                        IAS.setDataAboutSun(primaryStage, model);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            else {
                showMessage(continueButton.getScene().getWindow(),
                        "Обязательно нужно указать количество планет (цифрой или числом), " +
                                "название планетарной системы и выбрать масштаб времени");
            }
        });
    }

    public static void showMessage(Window window, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Что-то пошло не так...");
        alert.initOwner(window);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean isNumber(String s) {
        try {
            double i = Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
