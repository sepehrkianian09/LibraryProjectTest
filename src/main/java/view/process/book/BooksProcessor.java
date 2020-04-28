package view.process.book;

import control.book.BookController;
import view.process.FunctioningOption;
import view.process.Processor;
import view.process.person.ReceiverProcessor;

import java.util.HashMap;

public class BooksProcessor extends Processor {
    private static BookController bookController = BookController.getInstance();
    private static BooksProcessor booksProcessorInstance = null;

    private BooksProcessor(){
        functionsHashMap = new HashMap<String, FunctioningOption>();
        functionsHashMap.put("Show Unreceived Books", new FunctioningOption() {
            public String doTheThing() {
                return showUnreceivedBooks();
            }
        });
        functionsHashMap.put("Receive A Book", new FunctioningOption() {
            public String doTheThing() {
                return ReceiverProcessor.getInstance().receiveBook();
            }
        });
    }

    public static BooksProcessor getInstance(){
        if(booksProcessorInstance == null)
            booksProcessorInstance = new BooksProcessor();

        return booksProcessorInstance;
    }

    public String showUnreceivedBooks(){
        System.out.println(bookController.controlShowingUnreceivedBooks());
        return "Books Menu";
    }
}
