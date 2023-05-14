package model.Exeptions;

public class UpdateException extends Exception {
    public UpdateException(String message){
        super("Erreur lors de la modification du produit\n" + message);
    }
}

