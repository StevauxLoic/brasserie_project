package DataAccess;

import model.Exeptions.CloseConnectionException;
import model.Exeptions.CreateConnectionException;

import java.sql.SQLException;

public class CloseDataAccessConnection {
    public CloseDataAccessConnection(){

    }

    public static void closeConnection() throws CloseConnectionException, CreateConnectionException {
        try {
            SingletonConnection.closeConnection();
        } catch (SQLException exception) {
            throw new CloseConnectionException();
        }
    }
}
