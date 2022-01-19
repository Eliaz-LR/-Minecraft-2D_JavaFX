package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Enregistre l'état du monde
 */
public class SaveMonde {
    /**
     * Tableau d'int correspondant au tableau de Type actuel (a chaque EnumType particulier est associé une valeur précise)
     */
    int[][] tabSaveMonde;
    /**
     * Tableau servant à charger les données récupérées
     */
    Type[][] tabLoadMonde;
    int[][] mondeRecup;
    /**
     * Monde à enregistrer
     */
    Monde monde;

    /**
     * Instancie et donne à tabSaveMonde la même taille que le monde passé en paramètre.
     * @param monde Monde à enregistrer.
     */
    public SaveMonde(Monde monde){
        this.monde = monde;
        tabSaveMonde = new int[monde.xMax][monde.yMax];
    }

    /**
     * Transforme le tableau de Type en tableau d'int afin de le sérialiser
     */
    public void sauverMonde(){
        //remplacer type de bloc par chiffre
        try{

            for(int i = 0; i < monde.getYMax(); i++){
                for(int y = 0; y < monde.getXMax(); y++){

                    switch (monde.getTabType()[y][i].getType()){
                        default:
                        case Air:
                            //à chaque bloc d'air le tableau prendra 0
                            tabSaveMonde[y][i] = 0;
                            break;
                        case Roche :
                            tabSaveMonde[y][i] = 1;
                            break;
                        case Terre :
                            tabSaveMonde[y][i] = 2;
                            break;
                        case Herbe :
                            tabSaveMonde[y][i] = 3;
                            break;
                        case Bedrock :
                            tabSaveMonde[y][i] = 4;
                            break;
                        case Tronc :
                            tabSaveMonde[y][i] = 5;
                            break;
                        case Feuilles :
                            tabSaveMonde[y][i] = 6;
                            break;
                    }
                }
            }

            FileOutputStream fout = new FileOutputStream("f.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(tabSaveMonde);


            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Récupère les informations sur un monde précédemment enregistré.
     */
    private void recupMonde(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("f.txt"));
            mondeRecup = (int[][]) in.readObject();
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Passe le tableau d'int mondeRecup en un tableau de Type tabLoadMonde
     * @return tabLoadMonde : un tableau de Type
     */
    private Type[][] intToType(){
        int hauteur = mondeRecup.length;
        int largeur = mondeRecup[0].length;

        try{
            for(int i = 0; i < largeur; i++){
                for(int y = 0; y < hauteur; y++){

                    switch (mondeRecup[y][i]){
                        default:
                        case 0:
                            //à chaque bloc d'air le tableau prendra 0
                            tabLoadMonde[y][i] = new Type(EnumType.Air);
                            break;
                        case 1:
                            tabLoadMonde[y][i] = new Type(EnumType.Roche);
                            break;
                        case 2 :
                            tabLoadMonde[y][i] = new Type(EnumType.Terre);
                            break;
                        case 3 :
                            tabLoadMonde[y][i] = new Type(EnumType.Herbe);
                            break;
                        case 4 :
                            tabLoadMonde[y][i] = new Type(EnumType.Bedrock);
                            break;
                        case 5:
                            tabLoadMonde[y][i] = new Type(EnumType.Tronc);
                            break;
                        case 6:
                            tabLoadMonde[y][i] = new Type(EnumType.Feuilles);
                            break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tabLoadMonde;
    }

    /**
     * Permet de récupérer le dernier monde enregistré.
     * @return retourne un tableau de Type représentant le monde.
     */
    public Type[][] getMondeSauver(){
        recupMonde();
        return intToType();
    }

}
