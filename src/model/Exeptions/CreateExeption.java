package model.Exeptions;

public class CreateExeption extends Exception{
    public CreateExeption(String message){
        super("Erreur lors de la création du produit\n" + message);
    }

}
