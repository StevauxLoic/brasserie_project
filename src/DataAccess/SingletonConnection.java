package DataAccess;

public class SingletonConnection {
    private static  Connection uniqueConnection;

    public static Connection getInstance(){
        if(uniqueConnection == null){
            // creer la connection
        }
        return uniqueConnection;
    }
}
