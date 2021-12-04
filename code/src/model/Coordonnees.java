package model;

import javafx.scene.canvas.Canvas;

public class Coordonnees {
    public double x;
    public double y;
    public Coordonnees(){}
    public Coordonnees(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Coordonnees positionToCanvas(double xOnMonde, double yOnMonde, double xJoueur, double yJoueur, Canvas canvas, int cellSize){
        Coordonnees coo = new Coordonnees();
        coo.x = - xJoueur + canvas.getWidth()/2 + xOnMonde*cellSize;
        coo.y = - yJoueur + canvas.getHeight()/2 + yOnMonde*cellSize;
        return coo;
    }
    //positionToCanvas en inversé, ici xToConvert et yToConvert sont les coordonnées sur le canvas et coo.x et coo.y sont les coordonnées sur la map
    public Coordonnees CanvasToPosition(double xOnCanvas, double yOnCanvas, double xJoueur, double yJoueur, Canvas canvas, int cellSize){
        Coordonnees coo = new Coordonnees();
        coo.x = (xOnCanvas + xJoueur - canvas.getWidth()/2)/cellSize;
        coo.y = (yOnCanvas + yJoueur - canvas.getHeight()/2)/cellSize;
        return coo;
    }

}
