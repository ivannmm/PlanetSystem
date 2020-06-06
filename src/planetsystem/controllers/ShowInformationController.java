package planetsystem.controllers;

import javafx.stage.Stage;


public class ShowInformationController {

    Stage stage;

    public void setStage (Stage stage) {
        this.stage = stage;
    }

    public void closeStage () {
        stage.close();
    }

}
