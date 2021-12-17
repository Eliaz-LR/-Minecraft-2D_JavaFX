package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.GameManager;

public class Menu {
    @FXML
    private Button buttonPlay;

    @FXML private javafx.scene.control.Button buttonClose;

     //buttonPlay.setOnAction(event -> startGame(primaryStage)); fonctionnera quand il aura le singleton

    public void handleClose (ActionEvent actionEvent){
        // get a handle to the stage
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void handlePlay(ActionEvent actionEvent) {
        handleClose(actionEvent);
        GameManager.getInstance().startGame(100,30);

    }
}
