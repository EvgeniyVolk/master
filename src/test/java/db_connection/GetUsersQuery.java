package db_connection;

import org.testng.annotations.Test;

import java.sql.*;

public class GetUsersQuery {

    @Test
    public void getUsersQuery() throws Exception {

        GetJdbcConnection getJdbcConnection = new GetJdbcConnection();

        String query = "select count(*) from users";

        try {

            getJdbcConnection.init();

            // getting Statement object to execute query
            GetJdbcConnection.statement = GetJdbcConnection.connection.createStatement();

            // executing SELECT query
            GetJdbcConnection.result = GetJdbcConnection.statement.executeQuery(query);

            while (GetJdbcConnection.result.next()) {
                int count = GetJdbcConnection.result.getInt(1);
                System.out.println("Total number of users in the table : " + count);
            }

//            GetJdbcConnection.connection.createStatement().execute("SELECT * FROM users");

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                GetJdbcConnection.connection.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                GetJdbcConnection.statement.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                GetJdbcConnection.result.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

//    @Test
//    public void insertNewRecord() throws SQLException{
//
//        String query = "insert into userbugred.users (name, email, password) " +
//                "VALUES ('jdbcName2', 'jdbc2@mysql.com', 'jdbcpwd') ";
//
//        // getting Statement object to execute query
//        statement = connection.createStatement();
//
//        // executing SELECT query
//        statement.executeUpdate(query);
//    }
}

