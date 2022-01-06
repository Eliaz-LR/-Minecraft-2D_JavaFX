package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public Stage stage;

    public MenuController(Stage stage) {
        this.stage = stage;
    }
    public void LoadFXML(String FxmlURL) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlURL));
        Scene mainMenu = new Scene(loader.load());
        stage.setScene(mainMenu);
        stage.show();
    }
}
