package DataAccess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection uniqueConnection;

    public static Connection getUniqueConnection(){
        if(uniqueConnection == null){
            try {
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brasserie", "root", "admin") ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return uniqueConnection;
    }
}
