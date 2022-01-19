package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Permet de charger un fichier FXML
 */
public class MenuController {
    public Stage stage;

    public MenuController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Charge le fichier FXML passée en paramètre.
     * @param FxmlURL Url du fichier
     * @throws IOException
     */
    public void LoadFXML(String FxmlURL) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlURL));
        Scene mainMenu = new Scene(loader.load());
        stage.setScene(mainMenu);
        mainMenu.getWindow().centerOnScreen();
        stage.show();
    }
}
