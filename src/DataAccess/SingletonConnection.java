package DataAccess;

import model.Exeptions.CreateConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection uniqueConnection;

    public static Connection getUniqueConnection() throws CreateConnectionException {
        if(uniqueConnection == null){
            try {
                // insert the good user and password to be able to connect to the data base
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
