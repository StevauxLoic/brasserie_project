package DataAccess;

import model.Exeptions.CloseConnectionException;
import model.Exeptions.CreateConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection uniqueConnection;

    public static Connection getUniqueConnection() throws CreateConnectionException {
        if(uniqueConnection == null){
            try {
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_db", "root", "admin") ;
            } catch (SQLException exception) {
                throw new CreateConnectionException("la création de la connection d'accès aux données a échoué");
            }
        }
        return uniqueConnection;
    }

    public static void closeConnection() throws CloseConnectionException {
        try {
            SingletonConnection.getUniqueConnection().close();
        } catch (SQLException event) {
            throw new CloseConnectionException("la fermeture de la connexion d'accès aux données");
        } catch (CreateConnectionException event) {

        }
    }

}
