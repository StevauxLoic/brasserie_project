package model.Exeptions;

public class ModifyDatasException extends Exception {
    public ModifyDatasException(String message){
        super("Erreur lors de la modification du produit\n" + message);
    }
}

