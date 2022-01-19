package model;

import controller.MenuController;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.stage.WindowEvent;
import view.JoueurView;

import java.io.*;

public class GameManager {
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


    /**
     * Créé une instance de la classe GameManager si elle n'existe pas, sinon retourne gameManager.
     * @return Retourne l'instance gameManager
     */
    public final static  GameManager getInstance(){
        if(GameManager.gameManager == null){
            GameManager.gameManager = new GameManager();
        }
        return GameManager.gameManager;
    }

    /**
     * Affiche la page d'accueil du jeu.
     * @throws IOException
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
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                stop();
                Platform.exit();
                System.exit(0);
            }
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



        Joueur joueur = new Joueur();
        joueur.x = 1;
        joueur.y = 1;

        //je sais pas pourquoi mais le joueur n'est pas par defaut au milieu vraiment, le -18 est donc necessaire (optenu en faisant des experiences)
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
        gameTimeP.setValue(0);
        music = new Musique();
        music.playSound();


        final long startNanoTime = System.nanoTime();
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(15);
                    Platform.runLater(() -> {
                        gameTimeP.setValue(gameTimeP.getValue() + 1);
                        //System.out.println(deplacerJoueur.input); //affiche la touche dans le terminal
                        //converti et affiche les positions du joueur depuis le canvas vers le monde, les coordonées du joueur sont faites depuis son milieu.
                        Coordonnees coo_joueur_dans_monde = coo.CanvasToPosition(canvas.getWidth()/2,canvas.getHeight()/2, joueur.x, joueur.y,canvas, grid.cellSize);
                        //System.out.println(coo_joueur_dans_monde.x+" "+coo_joueur_dans_monde.y+" id: "+grid.monde.getType((int)coo_joueur_dans_monde.x,(int)coo_joueur_dans_monde.y).toString());


                        checkBlocks(grid, deplacerJoueur, coo_joueur_dans_monde);
                        joueurView.playerDirection();
                        deplacerJoueur.deplacerJoueur();
                        if (mouse.isCoordSet()){
                            Coordonnees coord_mouse = coo.CanvasToPosition(mouse.ClickedX, mouse.ClickedY, joueur.x, joueur.y,canvas, grid.cellSize);
                            if (Coordonnees.distanceBetweenCoords(canvas.getWidth()/2, canvas.getHeight()/2, mouse.ClickedX, mouse.ClickedY) < range){
                                if (mouse.mouseButton == MouseButton.PRIMARY){
                                    inv.setSlot(grid.monde.getType((int)coord_mouse.x, (int)coord_mouse.y));
                                    grid.monde.setType((int)coord_mouse.x,(int)coord_mouse.y,new Type(EnumType.Air));
                                    //sound.playSound("sounds/stoneBreakingplacing.mp3");
                                }
                                if (mouse.mouseButton == MouseButton.SECONDARY){
                                    grid.monde.setType((int)coord_mouse.x,(int)coord_mouse.y,new Type(EnumType.Roche));
                                }
                            }
                            else {
                                System.out.println("Vous ne pouvez pas detruire un bloc à cet endroit");
                            }
                            mouse.resetCoord();
                        }
                        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        cycle.changerEtat(canvas);
                        grid.drawMonde(canvas, joueur);
                        gc.drawImage(joueurView.img, canvas.getWidth()/2-widthSteve/2, canvas.getHeight()/2-heightSteve/2 );
                        viseur.drawViseur(canvas, mouse.X, mouse.Y);
                        viseur.drawTargetedCube(mouse.X, mouse.Y, joueur.x, joueur.y, canvas, blockSize, range);


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

    public void checkBlocks(DrawGrid grid, DeplacerJoueur deplacerJoueur, Coordonnees coo_joueur_dans_monde){
        //verification pour les blocs en DESSOUS
        if (grid.monde.getType((int)coo_joueur_dans_monde.x,1+(int)coo_joueur_dans_monde.y).toString().equals("Air") || grid.monde.getType((int)coo_joueur_dans_monde.x,1+(int)coo_joueur_dans_monde.y).toString().equals("Tronc")){
            deplacerJoueur.IsBlockDownEmpty = true;
        }
        else {
            deplacerJoueur.IsBlockDownEmpty = false;
        }

        //vérification pour les blocs au DESSUS
        if (grid.monde.getType((int)coo_joueur_dans_monde.x,(int)coo_joueur_dans_monde.y - 1).toString().equals("Air")){
            deplacerJoueur.IsBlockUpEmpty = true;
        }
        else {
            deplacerJoueur.IsBlockUpEmpty= false;
        }

        //vérification pour les blocs à GAUCHE (bloc du bas puis du haut)
        if(grid.monde.getType((int)(coo_joueur_dans_monde.x-0.5), (int)coo_joueur_dans_monde.y).toString().equals("Air") && grid.monde.getType((int)(coo_joueur_dans_monde.x-0.5), (int)coo_joueur_dans_monde.y-1).toString().equals("Air")){
            deplacerJoueur.IsBlockLeftEmpty = true;
        }
        else{
            deplacerJoueur.IsBlockLeftEmpty = false;
        }

        //vérification pour les blocs a DROITE (bloc du bas puis du haut)
        if(grid.monde.getType((int)(coo_joueur_dans_monde.x+0.5), (int)coo_joueur_dans_monde.y).toString().equals("Air") &&  grid.monde.getType((int)(coo_joueur_dans_monde.x+0.5), (int)(coo_joueur_dans_monde.y-1)).toString().equals("Air")){
            deplacerJoueur.IsBlockRightEmpty = true;
        }
        else{
            deplacerJoueur.IsBlockRightEmpty = false;
        }
    }

    /**
     * Coupe la musique et enregistre le monde dans un fichier.
     */
    public void stop(){
        //grid.afficherType();
        SaveMonde sm = new SaveMonde(monde);
        sm.sauverMonde();
        music.stopMusique();
    }
}
