package db;

import java.sql.*;

public class MySQLConnection {

    private Connection connection;

    public MySQLConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void connect() throws SQLException {
        connection= DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_11","P09728_1_11","ZCSaQGZU");
    }

    //SELECT
    public ResultSet getDataBySQL(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    //DELETE, EDIT, INSERT
    public void commandSQL(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }
    public void disconnect()throws SQLException{
        connection.close();
    }
}
