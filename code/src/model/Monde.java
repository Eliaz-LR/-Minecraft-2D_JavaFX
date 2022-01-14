package model;
import model.Type;
import model.EnumType;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Monde implements Serializable {
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

        //creation d'une couche de bedrock en y le plus bas
        for(int x = 0; x < xMax ; x++ ){
            for(int y = yMax-1; y > yMax-2; y-- ){
                type[x][y] = new Type(EnumType.Bedrock);
            }
        }

        // creation d'une couche de stone en y=le plus bas
        for (int x = 0; x < xMax; x++) {
            for (int y = yMax-10; y < yMax-1; y++) {
                type[x][y] = new Type(EnumType.Roche);
            }
        }

        // creation d'une couche de dirt au dessus de la roche
        int max = yMax-15;
        int min = yMax-9;
        int currentHeightGen=(min+max)/2;
        int randomNum = ThreadLocalRandom.current().nextInt(max, min);;
        for (int x = 0; x < xMax; x++) {
            if (x%7 == 0){
                randomNum = ThreadLocalRandom.current().nextInt(max, min);
            }
            if (randomNum > currentHeightGen){
                currentHeightGen++;
            }
            else if (randomNum < currentHeightGen){
                currentHeightGen--;
            }
            for (int y = currentHeightGen; y < yMax-9; y++) {
                type[x][y] = new Type(EnumType.Terre);
            }
        }

        int countForTree=0;
        int surface=1;
        for (int x = 0; x < xMax; x++) {
            // creation d'une couche de grass au dessus de la terre
            for (int y = yMax-10; y > yMax-16; y--) {
                if (type[x][y].getType() == EnumType.Terre && type[x][y-1].getType() == EnumType.Air) {
                    type[x][y-1] = new Type(EnumType.Herbe);
                    surface = y-2;
                }
            }
            //generation aleatoire des arbres
            if (x%10==0 && countForTree<=0){
                countForTree = ThreadLocalRandom.current().nextInt(10, 40);
            }
            if (countForTree==1){
                drawArbre(x,surface);
            }
            countForTree--;
        }
        type[0][0] = new Type(EnumType.Roche);

    }
    public Type getType(int x, int y){
        if (x<0 || x>=xMax || y<0 || y>=yMax){
            System.out.println("OUT OF BOUNDS");
            return new Type(EnumType.Air);
        }
        return type[x][y];
    }

    public Type[][] getTabType(){
        return type;
    }

    /**
     * Retourne la largeur maximale du monde
     * @return largeur maximale du monde
     */
    public int getXMax(){
        return xMax;
    }

    /**
     * Retourne la hauteur maximale du monde
     * @return hauteur maximale du monde
     */
    public int getYMax(){
        return yMax;
    }

    public void setType(int x, int y, Type t){
        if (x<0 || x>=xMax || y<0 || y>=yMax){
            System.out.println("Erreur : coordonnées hors du monde");
            return;
        }
        type[x][y] = t;
    }

    public void drawArbre(int x, int y){
        //place un arbre a partir de la position de son tronc
        setType(x,y,new Type(EnumType.Tronc));
        setType(x,y-1,new Type(EnumType.Tronc));
        setType(x,y-2,new Type(EnumType.Tronc));
        setType(x+1,y-3,new Type(EnumType.Feuilles));
        setType(x,y-3,new Type(EnumType.Feuilles));
        setType(x-1,y-3,new Type(EnumType.Feuilles));
        setType(x,y-4,new Type(EnumType.Feuilles));
    }






}
