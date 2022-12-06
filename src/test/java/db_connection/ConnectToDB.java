package db_connection;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

import static io.opentelemetry.semconv.trace.attributes.SemanticAttributes.DB_USER;
public class ConnectToDB {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/userbugred";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection connection;
    private static Statement statement;
    private static ResultSet result;

    @Test
    public void setUp() throws Exception {
        String query = "select count(*) from users";

        try {
            // opening database connection to MySQL server
            connection = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            statement = connection.createStatement();

            // executing SELECT query
            result = statement.executeQuery(query);

            while (result.next()) {
                int count = result.getInt(1);
                System.out.println("Total number of users in the table : " + count);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                connection.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                statement.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                result.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

    @Test
    public void insertNewRecord() throws SQLException{
        String query = "insert into userbugred.users (name, email, password) " +
                "VALUES ('jdbcName1', 'jdbc1@mysql.com', 'jdbcpwd') ";

        connection = DriverManager.getConnection(url, user, password);

        // getting Statement object to execute query
        statement = connection.createStatement();

        // executing SELECT query
        statement.executeUpdate(query);
    }
}

