package model;
import model.Type;
import model.EnumType;
import java.util.concurrent.ThreadLocalRandom;

public class Monde {
    /**
     * Tableau de Type représentant le monde
     */
    private Type[][] type;
    /**
     * Largeur de type maximum, donc du monde
     */
    public int xMax;
    /**
     * Hauteur de type maximum, donc du monde
     */
    public int yMax;

    /**
     * Constructeur qui créé les couches de bloc et fait apparaître des arbres aléatoirement.
     * @param xMax taille x maximum du monde
     * @param yMax taille y maximum du monde
     */
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

    /**
     * Permet de connaitre le Type d'une case du tableau type
     * @param x localisation x de la case dans le tableau
     * @param y localisation y de la case dans le tableau
     * @return le type correspondant au coordonnée passée en paramètre.
     */
    public Type getType(int x, int y){
        if (x<0 || x>=xMax || y<0 || y>=yMax){
            System.out.println("OUT OF BOUNDS");
            return new Type(EnumType.Air);
        }
        return type[x][y];
    }

    /**
     * Getter du tablau type
     * @return
     */
    public Type[][] getTabType(){
        return type;
    }

    /**
     * Permet de set le tableau de Type
     * @param tabInput
     */
    public void setTabType(Type[][] tabInput){
        this.type = tabInput;
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

    /**
     * Dessine arbre
     * @param x coordonnées x de la base de l'arbre
     * @param y coordonnées y de la base de l'arbre
     */
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
