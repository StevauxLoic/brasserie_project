package dataAccess;

import model.Exeptions.CloseConnectionException;
import model.Exeptions.CreateConnectionException;

import java.sql.SQLException;

public class CloseDataAccessConnection implements ICloseDataConnection {
    public CloseDataAccessConnection(){

    }

    public void closeConnection() throws CloseConnectionException, CreateConnectionException {
        try {
            SingletonConnection.closeConnection();
        } catch (SQLException exception) {
            throw new CloseConnectionException();
        }
    }
}
