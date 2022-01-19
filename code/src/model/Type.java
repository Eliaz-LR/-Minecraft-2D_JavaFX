package model;

import javafx.scene.image.Image;
import java.io.Serializable;

/**
 * Classe représentant un bloc dans le monde
 */
public class Type implements Serializable {
    /**
     * Défini le type comme étant un bloc spécifique (bloc d'air, de terre, etc.).
     */
    public EnumType type;
    /**
     * Texture du type
     */
    public Image texture;
    /**
     * Taille d'un type dans le monde
     */
    private final int cellSize;

    public Type(EnumType type){
        this.type = type;
        this.cellSize = 40;
        setTextureSwitch(type);
    }
    public Type(EnumType type, int cellSize) {
        this.type = type;
        this.cellSize = cellSize;
        setTextureSwitch(type);
    }

    /**
     * En fonction de l'EnumType d'un type lui assigne une texture qui correspond.
     */
    private void setTextureSwitch(EnumType type){
        switch (type){
            case Roche:
                this.texture = new Image("/images/Stone_(texture).png", cellSize, cellSize, false, false);
                break;
            case Terre:
                this.texture = new Image("/images/Dirt_(texture).png", cellSize, cellSize, false, false);
                break;
            case Herbe:
                this.texture = new Image("/images/Grass_(texture).png", cellSize, cellSize, false, false);
                break;
            case Bedrock:
                this.texture = new Image("/images/Bedrock_(texture).png", cellSize, cellSize, false, false);
                break;
            case Tronc:
                this.texture = new Image("/images/Wood_(texture).png", cellSize, cellSize, false, false);
                break;
            case Feuilles:
                this.texture = new Image("/images/Leaves_(texture).png", cellSize, cellSize, false, false);
                break;
            default:
                break;
        }
    }

    /**
     * Getter d" EnumType
     * @return Retourne l'EnumType de l'instance.
     */
    EnumType getType(){
        return type;
    }

    public String toString(){
        return type.toString();
    }
}
