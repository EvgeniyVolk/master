package db_connection;

import org.testng.annotations.BeforeTest;

import java.sql.*;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SetupJdbcConnection {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/userbugred";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    public static Connection connection;
    public static Statement statement;
    public static ResultSet result;

    // opening database connection to MySQL server
    public Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //Validate connection is established and not closed
    @BeforeTest
    public void init() throws SQLException {
        connection = getNewConnection();
        assertTrue(connection.isValid(1));
        assertFalse(connection.isClosed());
    }
}
