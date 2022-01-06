package launch;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

import model.Coordonnees;

public class Launch extends Application {

    public static void main(String[] args) {
        launch(args);
        GameManager.getInstance();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        GameManager.getInstance();
        GameManager.primaryStage = primaryStage;
        GameManager.getInstance().start();
    }
}
