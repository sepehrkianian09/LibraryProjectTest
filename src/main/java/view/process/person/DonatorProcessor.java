package view.process.person;

import control.person.DonatorController;

public class DonatorProcessor extends UserProcessor {
    private static DonatorController donatorController = DonatorController.getInstance();
    private static DonatorProcessor donatorProcessorInstance = null;

    protected DonatorProcessor(){
        super();

    }

    public static DonatorProcessor getInstance(){
        if(donatorProcessorInstance == null)
            donatorProcessorInstance = new DonatorProcessor();

        return donatorProcessorInstance;
    }
}
