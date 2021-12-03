package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import model.Type;
import model.EnumType;

public class drawGrid {
    public void drawGrid(Canvas canvas, double width, double height, int cellSize) {
        for (int i = 0; i < width; i += cellSize) {
            canvas.getGraphicsContext2D().strokeLine(i, 0, i, height);
        }
        for (int i = 0; i < height; i += cellSize) {
            canvas.getGraphicsContext2D().strokeLine(0, i, width, i);
        }
    }
    public void drawMonde(Canvas canvas, double width, double height, int cellSize) {
        Monde monde = new Monde(50,50);
        Image stone = new Image("/images/Stone_(texture).png", cellSize, cellSize, false, false);
        for (int i = 0; i < monde.xMax; i++) {
            for (int j = 0; j < monde.yMax; j++) {
                if (monde.getType(i, j).getType() == EnumType.Roche) {
                    canvas.getGraphicsContext2D().drawImage(stone, i * cellSize, j * cellSize);
                }
            }
        }

    }
}