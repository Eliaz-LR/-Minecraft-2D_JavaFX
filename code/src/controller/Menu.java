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


    public void handleClose (ActionEvent actionEvent){
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    public void handlePlay(ActionEvent actionEvent) {
        try{


//            Parent root = FXMLLoader.load(getClass().getResource("/fxml/selectWorld.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menuWorld.fxml"));
            Scene scene = new Scene(root, 1200, 800);
            Stage stage = (Stage) buttonClose.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
