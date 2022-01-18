package model;

import javafx.scene.canvas.Canvas;

/**
 * Classe gérant le passage de coordonnée du canvas en coordonnée dans le monde et vice-versa.
 */
public class Coordonnees {
    public double x;
    public double y;

    public Coordonnees(){}

    public Coordonnees(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * A partir d'une position dans le monde retourne la position qui correspond sur le canvas.
     * @param xOnMonde position x dans le monde
     * @param yOnMonde position y dans le monde
     * @param xJoueur position x du joueur dans le monde
     * @param yJoueur position y du joueur dans le monde
     * @param canvas canvas en question
     * @param cellSize taille d'un bloc
     * @return Instance de Coordonnée avec les nouvelles valeurs
     */
    public Coordonnees positionToCanvas(double xOnMonde, double yOnMonde, double xJoueur, double yJoueur, Canvas canvas, int cellSize){
        Coordonnees coo = new Coordonnees();
        coo.x = - xJoueur + canvas.getWidth()/2 + xOnMonde*cellSize;
        coo.y = - yJoueur + canvas.getHeight()/2 + yOnMonde*cellSize;
        return coo;
    }
    //positionToCanvas en inversé, ici coo.x et coo.y sont les coordonnées sur la map

    /**
     * A partir d'une position dans le canvas retourne la position qui correspond dans le monde.
     * @param xOnCanvas position x sur le canvas
     * @param yOnCanvas position y sur le canvas
     * @param xJoueur   position x du joueur sur le canvas
     * @param yJoueur   position y du joueur sur le canvas
     * @param canvas    le canvas en question
     * @param cellSize  taille d'un bloc
     * @return Instance de Coordonnée avec les nouvelles valeurs.
     */
    public Coordonnees CanvasToPosition(double xOnCanvas, double yOnCanvas, double xJoueur, double yJoueur, Canvas canvas, int cellSize){
        Coordonnees coo = new Coordonnees();
        coo.x = (xOnCanvas + xJoueur - canvas.getWidth()/2)/cellSize;
        coo.y = (yOnCanvas + yJoueur - canvas.getHeight()/2)/cellSize;
        return coo;
    }

}
