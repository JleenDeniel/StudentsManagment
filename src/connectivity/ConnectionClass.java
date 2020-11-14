package connectivity;

import java.sql.*;

public class ConnectionClass implements AutoCloseable {
    private String url = "jdbc:mysql://localhost/students?serverTimezone=Europe/Moscow&useSSL=false";
    private String user = "root";
    private String password = System.getenv("dbPassword");
    public Connection connection;

    public ConnectionClass() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }
    public ResultSet execQuery(String sql) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        return res;
    }

    public void  execUpdate(String sql) throws SQLException{
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(sql);
    }


    public PreparedStatement statement(String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
