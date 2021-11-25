package futuremctests;

import javafx.scene.canvas.Canvas;

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
        drawGrid(canvas, width, height, cellSize);
    }
}
