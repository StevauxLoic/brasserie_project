package model.Exeptions;

public class CreateException extends Exception{
    public CreateException(String message){
        super("Erreur lors de la création du produit\n" + message);
    }

}
