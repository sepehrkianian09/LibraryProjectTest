package model;

import java.io.File;
import java.sql.*;

public class Database {
    protected static Connection connection = null;
    protected static String localDBUrl = "jdbc:sqlite:database\\database.sqlite";
    protected static boolean hasInit = false;

    protected static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(!hasInit)
            initDataBase();
        if(connection == null)
            connection = DriverManager.getConnection(localDBUrl);
        return connection;
    }

    protected static void initDataBase() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        File file = new File("database");
        file.mkdir();

        try {
            connection = DriverManager.getConnection(localDBUrl);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM sqlitemaster WHERE name = 'Users'");
            if(!resultSet.next()){
                statement.execute("CREATE TABLE Users("
                        + "Username VARCHAR(16),"
                        + "Password VARCHAR(16),"
                        + "Firstname VARCHAR(16),"
                        + "Lastname VARCHAR(16),"
                        + "Type VARCHAR(8),"
                        + "primary key(Username)"
                        + ");");
            }

            resultSet = statement.executeQuery("SELECT name FROM sqlitemaster WHERE name = 'AllBooks'");
            if(!resultSet.next()){
                statement.execute("CREATE TABLE AllBooks("
                        + "ID INTEGER,"
                        + "Name VARCHAR(32),"
                        + "IsReceived BIT,"
                        + "ReceiverName VARCHAR(16) NOT NULL,"
                        + "DonatorName VARCHAR(16) NULLABLE,"
                        + "primary key(ID)"
                        + ")");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        hasInit = true;
    }

}
