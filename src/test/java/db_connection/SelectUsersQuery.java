package db_connection;

import org.testng.annotations.Test;

import java.sql.*;

public class SelectUsersQuery {

    @Test
    public void getUsersQuery() throws Exception {

        SetupJdbcConnection setupJdbcConnection = new SetupJdbcConnection();

        String query = "select count(*) from users";

        try {

            setupJdbcConnection.init();

            // getting Statement object to execute query
            SetupJdbcConnection.statement = SetupJdbcConnection.connection.createStatement();

            // executing SELECT query
            SetupJdbcConnection.result = SetupJdbcConnection.statement.executeQuery(query);

            while (SetupJdbcConnection.result.next()) {
                int count = SetupJdbcConnection.result.getInt(1);
                System.out.println("Total number of users in the table : " + count);
            }

//            SetupJdbcConnection.connection.createStatement().execute("SELECT * FROM users");

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                SetupJdbcConnection.connection.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                SetupJdbcConnection.statement.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                SetupJdbcConnection.result.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
}

