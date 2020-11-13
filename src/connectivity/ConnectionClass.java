package connectivity;

import java.sql.*;

public class ConnectionClass implements AutoCloseable {
    private String url = "jdbc:mysql://localhost/students?serverTimezone=Europe/Moscow&useSSL=false";
    private String user = "root";
    private String password = System.getenv("dbPassword");
    public Connection connection;

    public Connection ConnectionClass() throws SQLException {
        return this.connection = DriverManager.getConnection(url, user, password);
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
    private void closeConnection() throws SQLException {
        connection.close();
    }

    public PreparedStatement statement(String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement;
    }

    @Override
    public void close() throws SQLException {
        closeConnection();
    }
}
