package view.process.person;

import com.google.gson.Gson;
import control.person.ReceiverController;
import model.Packages;
import view.Menu;
import view.process.FunctioningOption;

import java.util.HashMap;

public class ReceiverProcessor extends UserProcessor {
    private static ReceiverController receiverController = ReceiverController.getInstance();
    private static ReceiverProcessor receiverProcessorInstance = null;

    protected ReceiverProcessor(){
        super();
        functionsHashMap.put("Get Received Books", new FunctioningOption() {
            public String doTheThing() {
                return getReceivedBooks();
            }
        });
        functionsHashMap.put("Receive Book", new FunctioningOption() {
            public String doTheThing() {
                return receiveBook();
            }
        });
        functionsHashMap.put("UnReceive Book", new FunctioningOption() {
            public String doTheThing() {
                return unReceiveBook();
            }
        });
    }

    public static ReceiverProcessor getInstance(){
        if(receiverProcessorInstance == null)
            receiverProcessorInstance = new ReceiverProcessor();

        return receiverProcessorInstance;
    }

    public String receiveBook(){
        if(ReceiverController.getLoginStatus()) {
            Packages.ReceivePackage receivePackage = new Packages.ReceivePackage();
            receivePackage.setReceiverName(ReceiverController.getLoginUserName());

            System.out.println("Please Enter The Book's Name :");
            receivePackage.setBookName(scanner.nextLine().trim());

            System.out.println("Please Enter The Donator's Name :");
            receivePackage.setDonatorName(scanner.nextLine().trim());

            System.out.println(receiverController.controlReceivingABook(new Gson().toJson(receivePackage)));
            return Menu.bookOrReceiver();
        } else {
            System.out.println("You must login first, man.");
            return "Book Menu";
        }
    }

    public String unReceiveBook(){
        Packages.ReceivePackage receivePackage = new Packages.ReceivePackage();
        receivePackage.setReceiverName(ReceiverController.getLoginUserName());

        System.out.println("Please Enter The Book's Name :");
        receivePackage.setBookName(scanner.nextLine().trim());

        System.out.println("Please Enter The Donator's Name :");
        receivePackage.setDonatorName(scanner.nextLine().trim());

        System.out.println(receiverController.controlUnReceivingABook(new Gson().toJson(receivePackage)));
        return "Receiver Menu";
    }

    public String getReceivedBooks(){
        System.out.println(receiverController.controlGettingTheReceivedBooks(ReceiverController.getLoginUserName()));
        return "Receiver Menu";
    }
}
