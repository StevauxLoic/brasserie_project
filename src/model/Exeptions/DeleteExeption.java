package model.Exeptions;

public class DeleteExeption extends Exception{
    public DeleteExeption(String message){
        super("Erreur lors de la suppresion du produit\n" + message);
    }
}

