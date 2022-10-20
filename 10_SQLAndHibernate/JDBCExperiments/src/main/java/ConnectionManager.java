import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/skillbox";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private ConnectionManager(){
    }

    public static Connection open(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
