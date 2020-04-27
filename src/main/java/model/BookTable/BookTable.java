package model.BookTable;

import com.google.gson.Gson;
import model.Database;
import model.Packages;

import java.sql.PreparedStatement;

public class BookTable extends Database {
    public void addBook(String bookPackageJson){
        Packages.BookPackage bookPackage = new Gson().fromJson(bookPackageJson, Packages.BookPackage.class);
        //TODO
    }


}
