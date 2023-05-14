package model.Exeptions;

public class DeleteException extends Exception{
    public DeleteException(String message){
        super("Erreur lors de la suppresion du produit\n" + message);
    }
}

