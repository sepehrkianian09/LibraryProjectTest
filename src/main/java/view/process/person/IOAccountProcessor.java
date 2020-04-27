package view.process.person;

import com.google.gson.Gson;
import control.person.IOAccountController;
import model.Packages;
import view.process.FunctioningOption;
import view.process.MainMenuProcessor;
import view.process.Processor;

import java.sql.SQLException;
import java.util.HashMap;

public class IOAccountProcessor extends Processor {
    private static IOAccountController ioAccountController = IOAccountController.getInstance();
    private static IOAccountProcessor ioAccountProcessor = null;

    private IOAccountProcessor(){
        functionsHashMap = new HashMap<String, FunctioningOption>();
        functionsHashMap.put("Register", new FunctioningOption() {
            public String doTheThing() {
                return register();
            }
        });
        functionsHashMap.put("Login", new FunctioningOption() {
            public String doTheThing(){
                return login();
            }
        });
    }

    public static IOAccountProcessor getInstance(){
        if(ioAccountProcessor == null)
            ioAccountProcessor = new IOAccountProcessor();

        return ioAccountProcessor;
    }

    public String register(){
        Packages.UserPackage userPackage = new Packages.UserPackage();
        boolean flag = true;
        String type = null;
        int input = 0;

        while(flag){
            flag = false;
            System.out.println("Please Enter The Type Of Your Account :" +
                    "\n1. Donator \n2. Receiver");
            input = Integer.parseInt(scanner.nextLine().trim());

            switch (input){
                case 1:
                    type = "Donator";
                    break;
                case 2:
                    type = "Receiver";
                    break;
                default:
                    System.out.println("Invalid Number!!! \nWhat are you doing, man?!");
                    flag = true;
                    break;
            }
        }

        userPackage.setType(type);

        System.out.println("Please Enter Your UserName :");
        userPackage.setUserName(scanner.nextLine().trim());

        System.out.println("Please Enter Your Password :");
        userPackage.setPassWord(scanner.nextLine().trim());

        System.out.println("Please Enter Your FirstName :");
        userPackage.setFirstName(scanner.nextLine().trim());

        System.out.println("Please Enter Your LastName :");
        userPackage.setLastName(scanner.nextLine().trim());

        System.out.println(ioAccountController.controlTheRegistering(new Gson().toJson(userPackage)));
        return "IOAccount Menu";
    }

    public String login() {
        Packages.LoginPackage loginPackage = new Packages.LoginPackage();

        System.out.println("Please Enter Your UserName :");
        loginPackage.setUserName(scanner.nextLine().trim());

        System.out.println("Please Enter Your PassWord :");
        loginPackage.setPassWord(scanner.nextLine().trim());

        System.out.println(ioAccountController.controlTheLogging(new Gson().toJson(loginPackage)));
        return MainMenuProcessor.getInstance().whereToGoInUserMenu();
    }

}
