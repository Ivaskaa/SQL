package workers;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
    private Connection connection;

    public JDBCConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=Ivaska", "Ivaska", "password");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
