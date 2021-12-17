package model;

import javafx.scene.canvas.Canvas;

public class DrawGrid {
    public int cellSize;
    public Monde monde;

    public DrawGrid(int cellSize, Monde monde) {
        this.cellSize = cellSize;
        this.monde = monde;
    }

    public void drawGrid(Canvas canvas, double width, double height, int cellSize) {
        for (int i = 0; i < width; i += cellSize) {
            canvas.getGraphicsContext2D().strokeLine(i, 0, i, height);
        }
        for (int i = 0; i < height; i += cellSize) {
            canvas.getGraphicsContext2D().strokeLine(0, i, width, i);
        }
    }
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
}