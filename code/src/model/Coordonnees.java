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
    public Coordonnees positionToCanvas(double xToConvert, double yToConvert, double xJoueur, double yJoueur, Canvas canvas){
        Coordonnees coo = new Coordonnees();
        coo.x = xToConvert - xJoueur + canvas.getWidth()/2;
        coo.y = yToConvert - yJoueur + canvas.getHeight()/2;
        return coo;
    }
    //positionToCanvas en inversé
    public Coordonnees CanvasToPosition(double xToConvert, double yToConvert, double xJoueur, double yJoueur, Canvas canvas){
        Coordonnees coo = new Coordonnees();
        coo.x = xToConvert + xJoueur - canvas.getWidth()/2;
        coo.y = yToConvert + yJoueur - canvas.getHeight()/2;
        return coo;
    }

}
