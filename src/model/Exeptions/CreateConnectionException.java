package model.Exeptions;

public class CreateConnectionException extends Throwable {
    public CreateConnectionException() {super("la création de la connection d'accès aux données a échoué");}
}
