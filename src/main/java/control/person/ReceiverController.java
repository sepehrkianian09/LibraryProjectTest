package control.person;

public class ReceiverController {
    private static ReceiverController receiverControllerInstance = null;

    private ReceiverController(){
        super();

    }

    public static ReceiverController getInstance(){
        if(receiverControllerInstance == null)
            receiverControllerInstance = new ReceiverController();

        return receiverControllerInstance;
    }
}
