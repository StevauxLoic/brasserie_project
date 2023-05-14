package DataAccess;

import model.Exeptions.CloseConnectionException;
import model.Exeptions.CreateConnectionException;

import javax.imageio.IIOException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection uniqueConnection;

    public static Connection getUniqueConnection() throws CreateConnectionException {
        if(uniqueConnection == null){
            try {
                uniqueConnection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/shop_db", "root", "admin") ;
            } catch (SQLException exception) {
                throw new CreateConnectionException();
            }
        }
        return uniqueConnection;
    }

    public static void closeConnection() throws SQLException, CreateConnectionException {
            SingletonConnection.getUniqueConnection().close();

    }

}
