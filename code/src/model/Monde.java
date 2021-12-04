package model;
import model.Type;
import model.EnumType;

public class Monde {
    private Type[][] type;
    public int xMax;
    public int yMax;
    public Monde(int xMax, int yMax){
        this.xMax = xMax;
        this.yMax = yMax;
        type = new Type[xMax][yMax];
        // Initialisation du monde avec de l'air partout
        for (int x = 0; x < xMax; x++) {
            for (int y = 0; y < yMax; y++) {
                type[x][y] = new Type(EnumType.Air);
            }
        }
        // creation d'une couche de stone en y=le plus bas
        for (int x = 0; x < xMax; x++) {
            for (int y = yMax-1; y > yMax-10; y--) {
                type[x][y] = new Type(EnumType.Roche);
            }
        }
        type[0][0] = new Type(EnumType.Roche);
    }
    public Type getType(int x, int y){
        return type[x][y];
    }
    public void setType(int x, int y, Type t){
        type[x][y] = t;
    }
}
