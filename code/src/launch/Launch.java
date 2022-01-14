package launch;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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

    /**
     * Arrête la musique lorsque le programme est fermé
     */
    @Override
    public void stop(){
        GameManager.getInstance().stop();
    }
}
