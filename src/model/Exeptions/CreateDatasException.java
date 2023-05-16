package model.Exeptions;

public class CreateDatasException extends Exception{
    public CreateDatasException(String message){
        super("Erreur lors de la création du produit\n" + message);
    }

}
