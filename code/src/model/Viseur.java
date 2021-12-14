package model;

import javafx.scene.canvas.Canvas;

public class Viseur {
    private double angle;
    private double x;
    private double y;

    public void drawViseur(Canvas canvas, double xSouris, double ySouris) {
        double delta_x = xSouris - canvas.getWidth()/2;
        double delta_y = ySouris - canvas.getHeight()/2;
        angle = Math.atan2(delta_y, delta_x);
        x = canvas.getWidth()/2 + 128 * Math.cos(angle);
        y = canvas.getHeight()/2 + 128 * Math.sin(angle);

        canvas.getGraphicsContext2D().strokeLine(canvas.getWidth()/2, canvas.getHeight()/2, x, y);
    }
}
