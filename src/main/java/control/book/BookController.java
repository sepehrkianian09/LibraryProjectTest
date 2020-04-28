package control.book;

import control.Controller;
import model.BookTable.BookTable;
import model.Packages;

import java.sql.SQLException;

public class BookController extends Controller {
    private static BookTable bookTable = BookTable.getInstance();
    private static BookController bookControllerInstance = null;

    private BookController(){
        super();

    }

    public static BookController getInstance(){
        if(bookControllerInstance == null)
            bookControllerInstance = new BookController();

        return bookControllerInstance;
    }

    public String controlShowingUnreceivedBooks(){
        try {
            if(bookTable.isThereAnyUnreceivedBook()){
                return Packages.BookPackage.toStringList(bookTable.getUnReceivedBooks());
            } else {
                return "There isn't Any Unreceived Book Left.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }


}
