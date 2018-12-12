
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    private final static String dbname = "kushisaloon.db";
    private static Connection connection;

    public static Connection createConnection() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        if (connection == null || connection.isClosed()) {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbname);
        }
        return connection;
    }
}
