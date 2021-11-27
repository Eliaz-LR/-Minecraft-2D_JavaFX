package model;
import model.EnumType;

public class Type {
    public EnumType type;

    public Type(EnumType type){
        this.type = type;
    }

    EnumType getType(){
        return type;
    }
}
