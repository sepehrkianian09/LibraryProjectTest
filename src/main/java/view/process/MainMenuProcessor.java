package view.process;

import control.Controller;
import control.person.UserController;

import java.util.HashMap;

public class MainMenuProcessor extends Processor{
    private static UserController userController = UserController.getInstance();
    private static MainMenuProcessor mainMenuProcessor = null;

    private MainMenuProcessor(){
        functionsHashMap = new HashMap<String, FunctioningOption>();
        functionsHashMap.put("User Menu", new FunctioningOption() {
            public String doTheThing() {
                return whereToGoInUserMenu();
            }
        });
    }

    public static MainMenuProcessor getInstance(){
        if(mainMenuProcessor == null)
            mainMenuProcessor = new MainMenuProcessor();

        return mainMenuProcessor;
    }

    public String whereToGoInUserMenu(){
        if(userController.getLoginStatus())
            return UserController.getLoginType() + " Menu";

        return "IOAccount Menu";
    }
}
