package control.person;

import com.google.gson.Gson;
import control.Controller;
import model.AccountTable.UserTable;
import model.Packages;

import java.sql.SQLException;

public class UserController extends Controller {
    private static UserTable userTable = UserTable.getInstance();
    private static UserController userControllerInstance = null;

    protected UserController(){

    }

    public static UserController getInstance(){
        if(userControllerInstance == null)
            userControllerInstance = new UserController();

        return userControllerInstance;
    }

    public String returnUserPackageJson(){
        try {
            return new Gson().toJson(userTable.getPerson(Controller.getLoginUserName()));
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String editUserField(String editFieldPackageJson){
        Packages.EditFieldPackage editFieldPackage = new Gson().fromJson(editFieldPackageJson, Packages.EditFieldPackage.class);

        if(!isTheFieldValid(editFieldPackage.getNewValue()))
            return "New " + editFieldPackage.getField() + " Value is Not Valid.";

        try {
            userTable.editTheField(editFieldPackage, UserController.getLoginUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Editing Successful.";
    }

    public String changePassWord(String changePassWordPackageJson){
        Packages.ChangePassWordPackage changePassWordPackage = new Gson().fromJson(changePassWordPackageJson, Packages.ChangePassWordPackage.class);

        try {
            if(!userTable.isPassWordCorrect(new Packages.LoginPackage(UserController.getLoginUserName(), changePassWordPackage.getOldPassWord()))){
                return "Old Password is Not Correct.";
            } else if(!isPassWordValid(changePassWordPackage.getNewPassWord())){
                return "New Password is Not Valid.";
            } else {
                userTable.editTheField(new Packages.EditFieldPackage("Password", changePassWordPackage.getNewPassWord()), UserController.getLoginUserName());
                return "Password Changed Successfully.";
            }
        } catch (SQLException e) {
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

    private boolean isTheFieldValid(String fieldValue){
        if(fieldValue.length() > 16)
            return false;

        return true;
    }
}
