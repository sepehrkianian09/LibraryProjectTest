package control.book;

import control.Controller;
import model.BookTable.BooksTable;
import model.Packages;

import java.sql.SQLException;

public class BookController extends Controller {
    private static BooksTable booksTable = BooksTable.getInstance();
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
            if(booksTable.isThereAnyUnreceivedBook()){
                return Packages.BookPackage.toStringList(booksTable.getUnReceivedBooks());
            } else {
                return "There isn't Any Unreceived Book Left.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }


}
