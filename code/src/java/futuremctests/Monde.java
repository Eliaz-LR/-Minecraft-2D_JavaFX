package futuremctests;
import futuremctests.Type;

public class Monde {
    private Type[][] type;
    public Monde(int xMax, int yMax) {
        type = new Type[xMax][yMax];
        // Initialisation du monde avec de l'air partout
        for (int x = 0; x < xMax; x++) {
            for (int y = 0; y < yMax; y++) {
                type[x][y] = new Type(0);
            }
        }
        // creation d'une couche de stone en y=0
        for (int x = 0; x < xMax; x++) {
            type[x][0] = new Type(1);
        }
    }
}
