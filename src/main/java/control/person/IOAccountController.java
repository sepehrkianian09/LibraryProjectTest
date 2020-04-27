package control.person;

import com.google.gson.Gson;
import control.Controller;
import model.AccountTable.UserTable;
import model.Database;
import model.Packages;

import java.sql.SQLException;

public class IOAccountController extends Controller {
    private static UserTable userTable = UserTable.getInstance();
    private static IOAccountController IOAccountControllerInstance = null;

    private IOAccountController(){

    }

    public static IOAccountController getInstance() {
        if (IOAccountControllerInstance == null)
            IOAccountControllerInstance = new IOAccountController();

        return IOAccountControllerInstance;
    }

    public String controlTheRegistering(String userPackageJson){
        Packages.UserPackage userPackage = new Gson().fromJson(userPackageJson, Packages.UserPackage.class);

        if(!isPassWordValid(userPackage.getPassWord())){
            return "PassWord is Not Valid.";
        } else if(areTheOtherFieldsValid(userPackage) != null){
            return areTheOtherFieldsValid(userPackage) + " is too long.";
        } else if(userTable.isThereUserWithUsername(userPackage.getUserName())){
            return "UserName is Not Valid.";
        }

        try {
            userTable.addPerson(userPackage);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Register Successful.";
    }

    public String controlTheLogging(String loginPackageJson) {
        Packages.LoginPackage loginPackage = new Gson().fromJson(loginPackageJson, Packages.LoginPackage.class);

        try {
            if (!userTable.isThereUserWithUsername(loginPackage.getUserName())) {
                return "UserName is Not Valid.";
            } else if (!userTable.isPassWordCorrect(loginPackage)) {
                return "Password is Not Correct.";
            } else {
                Controller.setLoginStatus(true);
                Controller.setLoginUserName(loginPackage.getUserName());
                Controller.setLoginType(userTable.getPerson(loginPackage.getUserName()).getType());
                return "Login Successful.";
            }
        } catch (SQLException e){
            e.printStackTrace();
            return "";
        }
    }

    private boolean isPassWordValid(String passWord){
        //TODO checking another validation
        if(passWord.length() < 8 || passWord.length() > 16)
            return false;

        return true;
    }

    private String areTheOtherFieldsValid(Packages.UserPackage userPackage){
        if(userPackage.getUserName().length() > 16){
            return "UserName";
        } else if(userPackage.getFirstName().length() > 16) {
            return "FirstName";
        } else if(userPackage.getLastName().length() > 16) {
            return "LastName";
        }

        return null;
    }
}
