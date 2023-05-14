package model.Exeptions;

public class SelectExeption extends Exception{
    public SelectExeption(String message, String information){
        super("Erreur lors de la récupération " + information + "\n" + message);
    }


}
