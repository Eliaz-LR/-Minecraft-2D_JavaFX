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
    public void drawTargetedCube(double xSouris, double ySouris, double xJoueur, double yJoueur, Canvas canvas, int cellSize){
        gc.setStroke(Color.BLACK);
        gc=canvas.getGraphicsContext2D();
        Coordonnees souris = new Coordonnees();
        souris = souris.CanvasToPosition(xSouris,ySouris,xJoueur,yJoueur,canvas,cellSize);
        souris.x = (int) souris.x;
        souris.y = (int) souris.y;
        souris = souris.positionToCanvas(souris.x,souris.y,xJoueur,yJoueur,canvas,cellSize);

        gc.strokeRect(souris.x,souris.y,cellSize,cellSize);
    }
}
