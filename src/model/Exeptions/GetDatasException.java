package model.Exeptions;

public class GetDatasException extends Exception{
    public GetDatasException(String message, String selectDataType){
        super("Erreur lors de la récupération de la liste de(s) " + selectDataType + "\n" + message);
    }
}
