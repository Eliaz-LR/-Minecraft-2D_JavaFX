package futuremctests;



public class Type {
    public int idType;
    public String nom;
    public Type(int idType) {
        this.idType = idType;
        switch (idType) {
            case 0:
                this.nom = "air";
                break;
            case 1:
                this.nom = "stone";
                break;
            default:
                this.nom = "error";
                break;
        }
    }
}
