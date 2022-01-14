package model;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import model.EnumType;

import java.io.Serializable;
import java.security.SecureRandomParameters;

public class Type implements Serializable {
    public EnumType type;
    public Image texture;
    private int cellSize;

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

    EnumType getType(){
        return type;
    }

    public String toString(){
        return type.toString();
    }
}
