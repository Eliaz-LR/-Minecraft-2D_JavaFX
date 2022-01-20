package model;

import controller.MenuController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.application.Platform;
import view.JoueurView;

import java.io.*;
import java.util.ArrayList;

/**
 * Gère le fonctionnement du jeu (Instanciation, boucle de jeu, etc.).
 */
public class GameManager implements Observable {
    /**
     * Instance de GameManager
     */
    private static GameManager gameManager;
    public static Stage primaryStage;
    public MenuController menuController;
    /**
     * Compteur du temps à partir de la création d'un monde.
     */
    public SimpleLongProperty gameTimeP = new SimpleLongProperty();
    private Musique music;
    private Monde monde;
    private DrawGrid grid;
    private Joueur joueur;
    /**
     * Tableau d'observateurs.
     */
    private ArrayList tabObservateur;

    public GameManager() {
        tabObservateur = new ArrayList<InvalidationListener>();
    }


    /**
     * Créé une instance de la classe GameManager si elle n'existe pas, sinon retourne gameManager.
     * @return Retourne l'instance gameManager
     */
    public static  GameManager getInstance(){
        if(GameManager.gameManager == null){
            GameManager.gameManager = new GameManager();
        }
        return GameManager.gameManager;
    }

    /**
     * Affiche la page d'accueil du jeu.
     */
    public void start() throws IOException {
        primaryStage.setTitle("Minecraft 2D");
        primaryStage.setResizable(false);
        this.menuController = new MenuController(primaryStage);
        menuController.LoadFXML("/fxml/menuSceneBuild.fxml");
    }

    public void startGame(int widthMonde, int heightMonde){
        Group root = new Group();
        Scene mainJeu = new Scene(root);
        primaryStage.setScene(mainJeu);

        //Permet de fermer le programme quand on ferme la fenêtre.
        primaryStage.setOnCloseRequest(e -> {
            stop();
            Platform.exit();
            System.exit(0);
        });

        Canvas canvas = new Canvas(1400, 800);
        root.getChildren().add(canvas);


        double widthSteve = 40;
        double heightSteve = 80;
        int blockSize = 40;
        int range = blockSize*4;


        Mouse mouse = new Mouse(mainJeu);
        Coordonnees coo = new Coordonnees();
        coo.x = canvas.getWidth()/2;
        coo.y = canvas.getHeight()/2;



        joueur = new Joueur();
        joueur.x = 1;
        joueur.y = 1;

        //Je ne sais pas pourquoi, mais le joueur n'est pas par défaut au milieu vraiment, le -18 est donc nécessaire (obtenu en faisant des experiences)
        JoueurView joueurView = new JoueurView(joueur, widthSteve, heightSteve);
        Coordonnees spawn = coo.positionToCanvas(widthMonde/2-18, 0,joueur.x,joueur.y,canvas,blockSize);
        joueur.x = spawn.x;
        DeplacerJoueur deplacerJoueur = new DeplacerJoueur(joueur, mainJeu);

        GraphicsContext gc = canvas.getGraphicsContext2D();


        gc.setStroke(Color.RED);

        monde = new Monde(widthMonde, heightMonde);
        grid = new DrawGrid(blockSize, monde);
        Viseur viseur = new Viseur();
        Inventory inv = new Inventory();
        inv.fillSlots();
        Cycle cycle = new Cycle();
        Time time = new Time();
        addListener(time);
        gameTimeP.setValue(0);
        music = new Musique();
        music.playSound();

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(15);
                    Platform.runLater(() -> {
                        gameTimeP.setValue(gameTimeP.getValue() + 1);
                        notifier();
                        //converti et affiche les positions du joueur depuis le canvas vers le monde, les coordonnées du joueur sont faites depuis son milieu.
                        Coordonnees coo_joueur_dans_monde = coo.CanvasToPosition(canvas.getWidth()/2,canvas.getHeight()/2, joueur.x, joueur.y,canvas, grid.cellSize);
                        Collisionneur.checkBlocks(grid, deplacerJoueur, coo_joueur_dans_monde);
                        joueurView.playerDirection();
                        deplacerJoueur.deplacerJoueur();
                        if (mouse.isCoordSet()){
                            Coordonnees coord_mouse = coo.CanvasToPosition(mouse.clickedX, mouse.clickedY, joueur.x, joueur.y,canvas, grid.cellSize);
                            if (Coordonnees.distanceBetweenCoords(canvas.getWidth()/2, canvas.getHeight()/2, mouse.clickedX, mouse.clickedY) < range){
                                if (mouse.mouseButton == MouseButton.PRIMARY){
                                    inv.setSlot(grid.monde.getType((int)coord_mouse.x, (int)coord_mouse.y));
                                    grid.monde.setType((int)coord_mouse.x,(int)coord_mouse.y,new Type(EnumType.Air));
                                }
                                if (mouse.mouseButton == MouseButton.SECONDARY){
                                    grid.monde.setType((int)coord_mouse.x,(int)coord_mouse.y,new Type(EnumType.Roche));
                                }
                            }
                            else {
                                System.out.println("Vous ne pouvez pas détruire un bloc à cet endroit");
                            }
                            mouse.resetCoord();
                        }
                        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        cycle.changerEtat(canvas);
                        grid.drawMonde(canvas, joueur);
                        gc.drawImage(joueurView.img, canvas.getWidth()/2-widthSteve/2, canvas.getHeight()/2-heightSteve/2 );
                        viseur.drawViseur(canvas, mouse.X, mouse.Y);
                        viseur.drawTargetedCube(mouse.X, mouse.Y, joueur.x, joueur.y, canvas, blockSize, range);


                        //Time
                        time.displayTime(canvas);
                        //Inventaire
                        inv.drawInventory(canvas);
                        inv.drawItems(canvas);

                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        primaryStage.show();
    }


    /**
     * Coupe la musique et enregistre le monde dans un fichier.
     */
    public void stop(){
        SaveMonde sm = new SaveMonde(monde);
        sm.sauverMonde();
        music.stopSound();
    }

    public long getTime(){

        return this.gameTimeP.getValue();
    }


    public void notifier(){
        for(int i = 0;i<tabObservateur.size(); i++){
            InvalidationListener invalidationListeners = (InvalidationListener) tabObservateur.get(i);
            invalidationListeners.invalidated(this);
        }
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        tabObservateur.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        tabObservateur.remove(invalidationListener);
    }
}
