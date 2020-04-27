package view.process.person;

import control.person.ReceiverController;
import view.process.FunctioningOption;

import java.util.HashMap;

public class ReceiverProcessor extends UserProcessor {
    private static ReceiverController receiverController = ReceiverController.getInstance();
    private static ReceiverProcessor receiverProcessorInstance = null;

    protected ReceiverProcessor(){
        super();

    }

    public static ReceiverProcessor getInstance(){
        if(receiverProcessorInstance == null)
            receiverProcessorInstance = new ReceiverProcessor();

        return receiverProcessorInstance;
    }
}
