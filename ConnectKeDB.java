import java.sql.*;

public class ConnectKeDB {
    private static final String URL = "jdbc:mysql://localhost:3306/sepaket";
    private static final String usn = "root";
    private static final String pwd = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, usn, pwd);
    }
}
