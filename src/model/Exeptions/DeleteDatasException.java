package model.Exeptions;

public class DeleteDatasException extends Exception{
    public DeleteDatasException(String message){
        super("Erreur lors de la suppresion du produit\n" + message);
    }
}

