package launch;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.Coordonnees;

public class Launch extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Hello World!");

        Group root = new Group();
        Scene mainJeu = new Scene(root);
        primaryStage.setScene(mainJeu);

        Canvas canvas = new Canvas(1000, 800);
        root.getChildren().add(canvas);
        Joueur joueur = new Joueur();
        deplacerJoueur deplacerJoueur = new deplacerJoueur(joueur, mainJeu);
        GraphicsContext gc = canvas.getGraphicsContext2D();





        Image image = new Image("/images/Stone_(texture).png");
        double widthSteve = 160/2;
        double heightSteve = 360/2;
        Image player = new Image("/images/steve.png", widthSteve, heightSteve, true, true);


        gc.setFill(Color.LIGHTBLUE);
        gc.setStroke(Color.DARKGRAY);
        drawGrid grid = new drawGrid();
        grid.drawGrid(canvas,canvas.getWidth(),canvas.getHeight(),10);
        Coordonnees coo = new Coordonnees();
        coo.x = canvas.getWidth()/2;
        coo.y = canvas.getHeight()/2;
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                deplacerJoueur.deplacerJoueur();
                System.out.println(deplacerJoueur.input); //affiche la touche dans le terminal
                System.out.println(joueur.x+" "+joueur.y);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                grid.drawMonde(canvas, canvas.getWidth(), canvas.getHeight(), 30);
                gc.drawImage(joueur.img, canvas.getWidth()/2-widthSteve/2, canvas.getHeight()/2-heightSteve/2);
            }
        }.start();

        primaryStage.show();
    }

}
