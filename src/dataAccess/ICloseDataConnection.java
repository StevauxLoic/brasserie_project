package dataAccess;

import model.Exeptions.CloseConnectionException;
import model.Exeptions.CreateConnectionException;

public interface ICloseDataConnection {
    public void closeConnection() throws CloseConnectionException, CreateConnectionException;
}
