package model;

import javafx.scene.canvas.Canvas;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 *  Gère l'affichage initial du monde au lancement du jeu.
 */
public class DrawGrid {
    /**
     * Taille d'un bloc dans le monde
     */
    public int cellSize;
    /**
     * Monde qui sera représenté sur le canvas
     */
    public Monde monde;

    /**
     * Créé une instance de DrawGrid.
     * @param cellSize taille d'un cube du monde.
     * @param monde monde qui sera représenté sur le canvas
     */
    public DrawGrid(int cellSize, Monde monde) {
        this.cellSize = cellSize;
        this.monde = monde;
    }

    /**
     * **DEPRECIATED**
     * @param canvas
     * @param width
     * @param height
     * @param cellSize
     */
    public void drawGrid(Canvas canvas, double width, double height, int cellSize) {
        for (int i = 0; i < width; i += cellSize) {
            canvas.getGraphicsContext2D().strokeLine(i, 0, i, height);
        }
        for (int i = 0; i < height; i += cellSize) {
            canvas.getGraphicsContext2D().strokeLine(0, i, width, i);
        }
    }

    /**
     * Dessine sur un canvas le monde à partir d'un tableau de Type
     * @param canvas Canvas sur lequel le monde va être dessiné.
     * @param joueur
     * @param width Largeur du canvas.
     * @param height Hauteur du canvas.
     */
    public void drawMonde(Canvas canvas, Joueur joueur, double width, double height) {
        Coordonnees canvasCoord = new Coordonnees();

        for (int i = 0; i < this.monde.xMax; i++) {
            for (int j = 0; j < this.monde.yMax; j++) {
                //converti la position des blocs dans le monde en position pour le canvas
                canvasCoord = canvasCoord.positionToCanvas(i, j,joueur.x,joueur.y,canvas,cellSize);
                //si bloc de roche, on dessine la roche
                if (this.monde.getType(i, j).getType() != EnumType.Air) {
                    canvas.getGraphicsContext2D().drawImage(this.monde.getType(i, j).texture, canvasCoord.x, canvasCoord.y);
                }
            }
        }

    }

    /**
     * Permet d'afficher le tableau type sous forme de caractère
     */
    public void afficherType(){
        int[][] tabMonde = new int[0][];
    //remplacer type de bloc par chiffre

        for(int i = 0; i < monde.getYMax(); i++){
            for(int y = 0; y < monde.getXMax(); y++){

                switch (monde.getTabType()[y][i].getType()){
                    case Air:
                        //mettre 0 dans case nouveau tableau
                        tabMonde[y][i] = 0;
                        break;
                }
                System.out.println(monde.getTabType()[y][i].toString());

            }
        }
    }

    public void saveMonde(){
        try{
            FileOutputStream fout = new FileOutputStream("f.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(monde.getTabType().toString());
            out.writeObject(monde);

            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}