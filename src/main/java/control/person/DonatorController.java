package control.person;

import model.AccountTable.UserTable;

public class DonatorController extends UserController{
    private static DonatorController donatorControllerInstance = null;

    private DonatorController(){
        super();

    }

    public static DonatorController getInstance(){
        if(donatorControllerInstance == null)
            donatorControllerInstance = new DonatorController();

        return donatorControllerInstance;
    }
}
