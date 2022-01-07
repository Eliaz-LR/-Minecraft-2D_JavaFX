package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        try{


            Parent root = FXMLLoader.load(getClass().getResource("/fxml/selectWorld.fxml"));
            Scene scene = new Scene(root, 300, 275);
            Stage stage = (Stage) buttonClose.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }


//        try {
//            GameManager.getInstance().menuController.LoadFXML("/fxml/selectWorld.fxml");
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        handleClose(actionEvent);
//        GameManager.getInstance().startGame(80,30);
    }


}
