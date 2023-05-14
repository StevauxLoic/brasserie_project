package model.Exeptions;

public class UpdateExeption extends Exception {
    public UpdateExeption(String message){
        super("Erreur lors de la modification du produit\n" + message);
    }
}

