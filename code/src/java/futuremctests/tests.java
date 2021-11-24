package futuremctests;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import futuremctests.drawGrid;

public class tests extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");

        Group root = new Group();
        Scene mainJeu = new Scene(root);
        primaryStage.setScene(mainJeu);

        Canvas canvas = new Canvas(900, 700);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image image = new Image(tests.class.getResourceAsStream("/futuremctests/Stone_(texture).png"));



        gc.setFill(Color.LIGHTBLUE);

        drawGrid grid = new drawGrid();
        grid.drawGrid(canvas,canvas.getWidth(),canvas.getHeight(),50);

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000005.0;
                double x = Math.sin(t) * 200 + 400;
                double y = Math.cos(t) * 200 + 300;
                //gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.drawImage(image, x, y);
            }
        }.start();

        primaryStage.show();
    }
}
