package model.AccountTable;

import model.Database;

import java.sql.*;

public class UserTable extends Database {
    public boolean isThereUserWithUsername(String username){
        try {
            Connection connection = DriverManager.getConnection(localDBUrl);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE Username = '" + username + "'");
            if(resultSet.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void addPerson(){

    }
}
