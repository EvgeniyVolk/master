package db_connection;

import java.sql.SQLException;

import static db_connection.SetupJdbcConnection.connection;
import static db_connection.SetupJdbcConnection.statement;

public class InsertUserQuery {

    public void insertNewRecord(String name, String email, String password) throws SQLException {

        SetupJdbcConnection setupJdbcConnection = new SetupJdbcConnection();
        setupJdbcConnection.init();

        statement = connection.createStatement();
        String query = "insert into userbugred.users (name, email, password) " +
                "VALUES (" + "'" + name + "'" + "," + "'" + email + "'" + "," + "'" + password + "'" + ")";

        // executing query
        statement.executeUpdate(query);
    }
}
