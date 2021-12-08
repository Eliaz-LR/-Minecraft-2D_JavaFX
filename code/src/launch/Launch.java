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
import view.PlayerView;

public class Launch extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Minecraft 2D");

        Group root = new Group();
        Scene mainJeu = new Scene(root);
        primaryStage.setScene(mainJeu);

        Canvas canvas = new Canvas(1000, 800);
        root.getChildren().add(canvas);

        double widthSteve = 40;
        double heightSteve = 90;
        Joueur joueur = new Joueur(widthSteve, heightSteve);
        int blockSize = 40;

        //PlayerView plv = new PlayerView(joueur);
        //deplacerJoueur deplacerPlv = new deplacerJoueur(joueur, mainJeu, plv);

        deplacerJoueur deplacerJoueur = new deplacerJoueur(joueur, mainJeu);
        GraphicsContext gc = canvas.getGraphicsContext2D();


        gc.setFill(Color.LIGHTBLUE);
        gc.setStroke(Color.DARKGRAY);
        drawGrid grid = new drawGrid(blockSize);
        Coordonnees coo = new Coordonnees();
        coo.x = canvas.getWidth()/2;
        coo.y = canvas.getHeight()/2;
        Mouse mouse = new Mouse(mainJeu);


        final long startNanoTime = System.nanoTime();
        //d'apres le prof, plutot utiliser un thread pour la boucle
        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                //System.out.println(deplacerJoueur.input); //affiche la touche dans le terminal
                //converti et affiche les positions du joueur depuis le canvas vers le monde, les coordonées du joueur sont faites depuis son milieu.
                Coordonnees coo_joueur_dans_monde = coo.CanvasToPosition(canvas.getWidth()/2,canvas.getHeight()/2, joueur.x, joueur.y,canvas, grid.cellSize);
                //System.out.println(coo_joueur_dans_monde.x+" "+coo_joueur_dans_monde.y+" id: "+grid.monde.getType((int)coo_joueur_dans_monde.x,(int)coo_joueur_dans_monde.y).toString());
                if (grid.monde.getType((int)coo_joueur_dans_monde.x,1+(int)coo_joueur_dans_monde.y).toString().equals("Air")){
                    deplacerJoueur.IsBlockDownEmpty = true;
                }
                else {
                    deplacerJoueur.IsBlockDownEmpty = false;
                }
                deplacerJoueur.deplacerJoueur();
                if (mouse.coordSet()){
                    Coordonnees coord_mouse = coo.CanvasToPosition(mouse.x, mouse.y, joueur.x, joueur.y,canvas, grid.cellSize);
                    grid.monde.setType((int)coord_mouse.x,(int)coord_mouse.y,new Type(EnumType.Air));
                    mouse.resetCoord();
                }
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                grid.drawMonde(canvas, joueur, canvas.getWidth(), canvas.getHeight());
                gc.drawImage(joueur.img, canvas.getWidth()/2-widthSteve/2, canvas.getHeight()/2-heightSteve/2);
                //gc.drawImage(plv.img, canvas.getWidth()/2-widthSteve/2, canvas.getHeight()/2-heightSteve/2);
            }
        }.start();

        primaryStage.show();
    }

}
