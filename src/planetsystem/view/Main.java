package planetsystem.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.Window;
import planetsystem.controllers.PrimaryController;
import planetsystem.model.Model;


public class Main extends Application {

    Model model = new Model();



    @Override
    public void start(Stage primaryStage) throws Exception{

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/primaryInformation.fxml"));
        root.getChildren().add(loader.load());
        PrimaryController primaryController = loader.getController();

        primaryController.setModel(model);
        primaryController.setStage(primaryStage);

        primaryStage.setScene(theScene);
        primaryStage.show();
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
