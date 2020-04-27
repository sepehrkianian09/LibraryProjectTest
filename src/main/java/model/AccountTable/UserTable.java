package model.AccountTable;

import com.google.gson.Gson;
import model.Database;
import model.Packages;

import javax.jws.soap.SOAPBinding;
import java.sql.*;

public class UserTable extends Database {
    private static UserTable userTableInstance = null;

    public static UserTable getInstance(){
        if(userTableInstance == null)
            userTableInstance = new UserTable();

        return userTableInstance;
    }

    public boolean isThereUserWithUsername(String userName){
        try {
            Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE Username = ?;");
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isPassWordCorrect(Packages.LoginPackage loginPackage) throws SQLException {
            Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Password FROM Users WHERE Username = ?;");
            preparedStatement.setString(1, loginPackage.getUserName());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("Password").equals(loginPackage.getPassWord());
    }

    public void addPerson(Packages.UserPackage userPackage) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users VALUES(?, ?, ?, ?, ?)");

        preparedStatement.setString(1, userPackage.getUserName());
        preparedStatement.setString(2, userPackage.getPassWord());
        preparedStatement.setString(3, userPackage.getFirstName());
        preparedStatement.setString(4, userPackage.getLastName());
        preparedStatement.setString(5, userPackage.getType());

        preparedStatement.execute();
    }

    public void editTheField(Packages.EditFieldPackage editFieldPackage, String userName) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Users SET "
                + editFieldPackage.getField() +"= ? WHERE Username = ?;");

        preparedStatement.setString(1, editFieldPackage.getNewValue());
        preparedStatement.setString(2, userName);
        preparedStatement.execute();
    }

    public Packages.UserPackage getPerson(String userName) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE Username = ?;");
        preparedStatement.setString(1, userName);
        return turnUserRecordIntoUserPackage(preparedStatement.executeQuery());
    }

    public Packages.UserPackage turnUserRecordIntoUserPackage(ResultSet userResultSet) throws SQLException {
        Packages.UserPackage userPackage = new Packages.UserPackage();
        userPackage.setUserName(userResultSet.getString("Username"));
        userPackage.setPassWord(userResultSet.getString("Password"));
        userPackage.setFirstName(userResultSet.getString("Firstname"));
        userPackage.setLastName(userResultSet.getString("Lastname"));
        userPackage.setType(userResultSet.getString("Type"));
        return userPackage;
    }
}
