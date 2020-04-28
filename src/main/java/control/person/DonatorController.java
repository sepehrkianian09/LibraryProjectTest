package control.person;

import com.google.gson.Gson;
import model.BookTable.BookTable;
import model.Packages;

import java.sql.SQLException;

public class DonatorController extends UserController{
    private static BookTable bookTable = BookTable.getInstance();
    private static DonatorController donatorControllerInstance = null;

    private DonatorController(){
        super();

    }

    public static DonatorController getInstance(){
        if(donatorControllerInstance == null)
            donatorControllerInstance = new DonatorController();

        return donatorControllerInstance;
    }

    public String controlTheDonating(String donatePackageJson){
        Packages.DonatePackage donatePackage = new Gson().fromJson(donatePackageJson, Packages.DonatePackage.class);

        try {
            if(bookTable.isThereBookWithThisSpecs(donatePackage)){
                return "This Book has been Donated before.";
            } else {
                bookTable.addBook(new Packages.BookPackage(donatePackage));
                return "Donated Successfully";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String controlGettingDonatedBooks(String donatorName){
        try {
            if(bookTable.hasDonatedABookBefore(donatorName)){
                return Packages.BookPackage.toStringList(bookTable.getDonatedBooksByPerson(donatorName));
            } else {
               return "You haven't Donated A Book Before.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

}
