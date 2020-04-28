package view.process.book;

import control.book.BookController;
import view.process.FunctioningOption;
import view.process.Processor;
import view.process.person.IOAccountProcessor;
import view.process.person.ReceiverProcessor;

import java.util.HashMap;

public class BookProcessor extends Processor {
    private static BookController bookController = BookController.getInstance();
    private static BookProcessor bookProcessorInstance = null;

    private BookProcessor(){
        functionsHashMap = new HashMap<String, FunctioningOption>();
        functionsHashMap.put("Show Unreceived Books", new FunctioningOption() {
            public String doTheThing() {
                return showUnreceivedBooks();
            }
        });
        functionsHashMap.put("Receive Book", new FunctioningOption() {
            public String doTheThing() {
                return ReceiverProcessor.getInstance().receiveBook();
            }
        });
    }

    public static BookProcessor getInstance(){
        if(bookProcessorInstance == null)
            bookProcessorInstance = new BookProcessor();

        return bookProcessorInstance;
    }

    public String showUnreceivedBooks(){
        System.out.println(bookController.controlShowingUnreceivedBooks());
        return "Book Menu";
    }
}
