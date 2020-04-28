package model;

import java.io.File;
import java.sql.*;

public class Database {
    protected static Connection connection = null;
    protected static String localDBUrl = "jdbc:sqlite:database\\database.sqlite";
    protected static boolean hasInit = false;

    protected static void getConnection() throws SQLException {
        if(!hasInit)
            initDataBase();
        if(connection == null)
            connection = DriverManager.getConnection(localDBUrl);
    }

    protected static void initDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        File file = new File("database");
        file.mkdir();

        try {
            connection = DriverManager.getConnection(localDBUrl);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE name = 'Users'");
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

            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE name = 'Books'");
            if(!resultSet.next()){
                statement.execute("CREATE TABLE Books("
                        + "ID INTEGER,"
                        + "Name VARCHAR(32),"
                        + "IsReceived BIT,"
                        + "ReceiverName VARCHAR(16),"
                        + "DonatorName VARCHAR(16),"
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
