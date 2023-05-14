package model.Exeptions;

public class CloseConnectionException extends Throwable {
    public CloseConnectionException() {super(("la fermeture de la connexion d'accès aux données a échoué"));    }
}
