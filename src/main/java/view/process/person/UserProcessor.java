package view.process.person;

import com.google.gson.Gson;
import control.Controller;
import control.person.UserController;
import model.Packages;
import view.process.FunctioningOption;
import view.process.Processor;

import java.sql.SQLException;
import java.util.HashMap;

public class UserProcessor extends Processor {
    private static UserController userController = UserController.getInstance();
    private static UserProcessor userProcessorInstance = null;

    protected UserProcessor(){
        functionsHashMap = new HashMap<String, FunctioningOption>();
        functionsHashMap.put("Show Personal Specs", new FunctioningOption() {
            public String doTheThing() {
                return showPersonalSpecs();
            }
        });
        functionsHashMap.put("Edit Field", new FunctioningOption() {
            public String doTheThing() {
                return editField();
            }
        });
        functionsHashMap.put("Change Password", new FunctioningOption() {
            public String doTheThing() {
                return changePassWord();
            }
        });

    }

    public static UserProcessor getInstance(){
        if(userProcessorInstance == null)
            userProcessorInstance = new UserProcessor();

        return userProcessorInstance;
    }

    public String showPersonalSpecs(){
        Packages.UserPackage userPackage = new Gson().fromJson(userController.returnUserPackageJson(), Packages.UserPackage.class);
        System.out.println(userPackage.toString());
        return UserController.getLoginType() + " Menu";
    }

    public String editField(){
        Packages.EditFieldPackage editFieldPackage = new Packages.EditFieldPackage();
        System.out.println("Please Select the Field You Wanna Edit :");
        boolean flag = true;
        String field = null;
        int input = 0;

        while(flag){
            flag = false;
            System.out.println("1. Firstname" + "\n2. Lastname" + "\n3. Cancel");
            input = Integer.parseInt(scanner.nextLine().trim());

            switch (input){
                case 1:
                    field = "Firstname";
                    break;
                case 2:
                    field = "Lastname";
                    break;
                case 3:
                    return UserController.getLoginType() + " Menu";
                default:
                    System.out.println("Invalid Number!!! \nWhat are you doing, man?!");
                    flag = true;
                    break;
            }
        }

        System.out.println("Please Enter The New Value Of " + field);
        editFieldPackage.setNewValue(scanner.nextLine().trim());
        editFieldPackage.setField(field);

        System.out.println(userController.editUserField(new Gson().toJson(editFieldPackage)));
        return UserController.getLoginType() + " Menu";
    }

    public String changePassWord(){
        Packages.ChangePassWordPackage changePassWordPackage = new Packages.ChangePassWordPackage();

        System.out.println("First, Please Enter Your Old Password :");
        changePassWordPackage.setOldPassWord(scanner.nextLine().trim());

        System.out.println("Then, Please Enter Your New Password :");
        changePassWordPackage.setNewPassWord(scanner.nextLine().trim());

        System.out.println(userController.changePassWord(new Gson().toJson(changePassWordPackage)));
        return UserController.getLoginType() + " Menu";
    }

}
