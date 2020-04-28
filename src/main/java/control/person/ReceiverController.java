package control.person;

import com.google.gson.Gson;
import model.BookTable.BookTable;
import model.Packages;

import java.sql.SQLException;

public class ReceiverController extends UserController{
    private static BookTable bookTable = BookTable.getInstance();
    private static ReceiverController receiverControllerInstance = null;

    private ReceiverController(){
        super();

    }

    public static ReceiverController getInstance(){
        if(receiverControllerInstance == null)
            receiverControllerInstance = new ReceiverController();

        return receiverControllerInstance;
    }

    public String controlReceivingABook(String receivePackageJson){
        Packages.ReceivePackage receivePackage = new Gson().fromJson(receivePackageJson, Packages.ReceivePackage.class);

        try {
            if(!bookTable.isThereBookWithThisSpecs(receivePackage)){
                return "A Book With these Specs is Not Available.";
            } else if(bookTable.isTheBookWithThisSpecsReceived(receivePackage)){
                return "A Book With these Specs is Received Before";
            } else {
                bookTable.receiveBook(receivePackage);
                return "The Book Received Successfully.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String controlUnReceivingABook(String receivePackageJson){
        Packages.ReceivePackage receivePackage = new Gson().fromJson(receivePackageJson, Packages.ReceivePackage.class);

        try {
            if(!bookTable.isThereBookWithThisSpecs(receivePackage)){
                return "A Book With these Specs is Not Available.";
            } else if(!bookTable.isTheBookWithThisSpecsReceived(receivePackage)){
                return "A Book With these Specs is Not Received Before";
            } else {
                bookTable.unReceiveBook(receivePackage);
                return "The Book UnReceived Successfully";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String controlGettingTheReceivedBooks(String receiverName){
        try {
            if(bookTable.hasReceivedABookBefore(receiverName)){
                return Packages.BookPackage.toStringList(bookTable.getReceivedBooksByPerson(receiverName));
            } else {
                return "You haven't Received A Book Before.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
}
