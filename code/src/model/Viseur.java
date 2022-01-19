package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;


/**
 * Gère le surlignage du bloc observé et rotation de la tête
 */
public class Viseur {
    private double angleRad;
    private double angleDegre;
    private double x;
    private double y;
    private static final double width=35;
    private static final double height=35;
    private GraphicsContext gc;
    private Image img;
    private final Image imgD= new Image("/images/steve_head.png", width, height, false, true);
    private final Image imgG= new Image("/images/steve_head2.png", width, height, false, true);

    /**
     * Dessine la tête du joueur pointant vers la souris
     * @param canvas Canvas sur lequel la tête va être dessinée.
     * @param xSouris Coordonnée x de la souris.
     * @param ySouris Coordonnée y de la souris.
     */
    public void drawViseur(Canvas canvas, double xSouris, double ySouris) {
        gc = canvas.getGraphicsContext2D();
        double CentreX = canvas.getWidth()/2;
        double CentreY = canvas.getHeight()/2-30;
        double delta_x = xSouris - CentreX;
        double delta_y = ySouris - CentreY;
        angleRad = Math.atan2(delta_y, delta_x);
        angleDegre = Math.toDegrees(angleRad);
        x = CentreX + 128 * Math.cos(angleRad);
        y = CentreY + 128 * Math.sin(angleRad);
        //gc.strokeLine(CentreX, CentreY, x, y);
        gc.save();
        rotate(angleDegre, CentreX, CentreY);
        if (angleDegre <= 90 && angleDegre >= -90) {
            img = imgD;
        }
        else {
            img = imgG;
        }
        gc.drawImage(img, CentreX-width/2, CentreY-height/2);
        gc.restore();
    }

    /**
     * Permet de retourner le GraphicsContext à un angle donné autour d'un point donné.
     * @param angle Angle de rotation.
     * @param centreX Coordonnée x du centre du canvas.
     * @param centreY Coordonnée y du centre du canvas.
     */
    private void rotate(double angle, double centreX, double centreY) {
        Rotate r = new Rotate(angle, centreX, centreY);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     * Dessine autour du bloc visé par le joueur
     * @param xSouris Coordonnée x de la souris
     * @param ySouris Coordonnée y de la souris
     * @param xJoueur Coordonnée x du joueur
     * @param yJoueur Coordonnée y du joueur
     * @param canvas Canvas sur lequel le monde est dessiné
     * @param cellSize Taille d'un bloc.
     * @param range Distance maximum à laquelle un bloc est atteingnable par le joueur.
     */
    public void drawTargetedCube(double xSouris, double ySouris, double xJoueur, double yJoueur, Canvas canvas, int cellSize, int range){
        gc.setStroke(Color.BLACK);
        gc=canvas.getGraphicsContext2D();
        Coordonnees coordSouris = new Coordonnees();
        double distance = Coordonnees.distanceBetweenCoords(xSouris,ySouris,canvas.getWidth()/2,canvas.getHeight()/2);
        coordSouris = coordSouris.CanvasToPosition(xSouris,ySouris,xJoueur,yJoueur,canvas,cellSize);
        coordSouris.x = (int) coordSouris.x;
        coordSouris.y = (int) coordSouris.y;
        coordSouris = coordSouris.positionToCanvas(coordSouris.x,coordSouris.y,xJoueur,yJoueur,canvas,cellSize);
        if (distance<range){
            gc.strokeRect(coordSouris.x,coordSouris.y,cellSize,cellSize);
        }
    }
}
