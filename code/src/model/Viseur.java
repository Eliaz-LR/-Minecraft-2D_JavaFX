package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class Viseur {
    private double angleRad;
    private double angleDegre;
    private double x;
    private double y;
    private double width=35;
    private double height=35;
    private GraphicsContext gc;
    private Image img;
    private Image imgD= new Image("/images/steve_head.png", width, height, false, true);
    private Image imgG= new Image("/images/steve_head2.png", width, height, false, true);


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

    private void rotate(double angle, double centreX, double centreY) {
        Rotate r = new Rotate(angle, centreX, centreY);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
    public void drawTargetedCube(double xSouris, double ySouris, double xJoueur, double yJoueur, Canvas canvas, int cellSize, int range){
        gc.setStroke(Color.BLACK);
        gc=canvas.getGraphicsContext2D();
        Coordonnees coordSouris = new Coordonnees();
        double distance = distanceBetweenCoords(xSouris,ySouris,canvas.getWidth()/2,canvas.getHeight()/2);
        coordSouris = coordSouris.CanvasToPosition(xSouris,ySouris,xJoueur,yJoueur,canvas,cellSize);
        coordSouris.x = (int) coordSouris.x;
        coordSouris.y = (int) coordSouris.y;
        coordSouris = coordSouris.positionToCanvas(coordSouris.x,coordSouris.y,xJoueur,yJoueur,canvas,cellSize);
        if (distance<range){
            gc.strokeRect(coordSouris.x,coordSouris.y,cellSize,cellSize);
        }
    }
    private double distanceBetweenCoords(double x1, double y1, double x2, double y2){
        return Math.hypot(x1-x2, y1-y2);
    }
}
