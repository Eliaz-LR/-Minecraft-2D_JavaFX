package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Type;
import model.EnumType;
import model.Coordonnees;

public class drawGrid {
    public int cellSize;
    public Monde monde;

    public drawGrid() {
        this.monde = new Monde(50,20);
    }
    public drawGrid(int cellSize){
        this.cellSize = cellSize;
        this.monde = new Monde(50,20);
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
        //Image stone = new Image("/images/Stone_(texture).png", cellSize, cellSize, false, false);
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