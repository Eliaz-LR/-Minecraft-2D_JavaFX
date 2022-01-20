package launch;

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;

/**
 * Lance le jeu
 */
public class Launch extends Application {

    public static void main(String[] args) {
        launch(args);
        GameManager.getInstance();
    }

    /**
     * Affiche la page d'accueil du jeu.
     */
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
