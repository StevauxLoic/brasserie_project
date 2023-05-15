package model.Exeptions;

public class SelectException extends Exception{
    public SelectException(String message, String selectDataType){
        super("Erreur lors de la récupération de la liste de(s) " + selectDataType + "\n" + message);
    }
}
