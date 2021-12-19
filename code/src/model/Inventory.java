package model;

import javafx.scene.canvas.Canvas;

public class Inventory {

    public Type[] slots;

    public Inventory(){
        slots = new Type[9];
    }

    public void drawInventory(Canvas canvas){
        //largeur inv 540px hauteur 60px
        int largeurCase = 0;
        for (int i =0;i<9;i++){
            canvas.getGraphicsContext2D().strokeRect(canvas.getWidth()/2-270 + largeurCase, canvas.getHeight()- 100, 60,60);
            largeurCase= largeurCase + 60;
        }
    }


    public void fillSlots(){
        for(int i = 0; i<9;i++){
            slots[i] = new Type(EnumType.Air);
        }
    }

    public void setSlot(Type type){

        for (int i = 0; i<9 ; i++){
            //éviter de mettre un type de bloc dans une case qui est déjà utilisée
            if(slots[i].type.equals(EnumType.Air) || slots[i].type.equals(type.type)) {
                slots[i] = type;
                break;
            }


        }
    }

    public void drawItems(Canvas canvas){
        int largeurCase = 0;
        for(int i = 0; i<9; i++){


                canvas.getGraphicsContext2D().drawImage(slots[i].texture, canvas.getWidth()/2-265 + largeurCase, canvas.getHeight()- 95, 50, 50);
                largeurCase= largeurCase + 60;

        }
    }

    public String ToString(){

        String s = "";
        for(int i =0;i<9;i++){
            s = s + slots[i].type.toString();
        }

        return s;
    }

}
