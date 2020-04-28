package model.BookTable;

import model.Database;
import model.Packages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BooksTable extends Database {
    private static BooksTable booksTableInstance = null;

    public static BooksTable getInstance(){
        if(booksTableInstance == null)
            booksTableInstance = new BooksTable();

        return booksTableInstance;
    }

    public void addBook(Packages.BookPackage bookPackage) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Books " +
                "(Name, IsReceived, ReceiverName, DonatorName)" +
                "\nVALUES (?, ?, ?, ?);");

        preparedStatement.setString(1, bookPackage.getName());
        preparedStatement.setBoolean(2, bookPackage.isReceived());
        preparedStatement.setString(3, bookPackage.getReceiverName());
        preparedStatement.setString(4, bookPackage.getDonatorName());

        preparedStatement.execute();
    }

    public boolean isThereBookWithThisSpecs(Packages.DonatePackage donatePackage) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Books WHERE Name = ? AND DonatorName = ?;");

        preparedStatement.setString(1, donatePackage.getBookName());
        preparedStatement.setString(2, donatePackage.getDonatorName());
        return preparedStatement.executeQuery().next();
    }

    public boolean isTheBookWithThisSpecsReceived(Packages.DonatePackage donatePackage) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Books WHERE Name = ? AND DonatorName = ?;");

        preparedStatement.setString(1, donatePackage.getBookName());
        preparedStatement.setString(2, donatePackage.getDonatorName());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean("IsReceived");
    }

    public boolean isThereAnyUnreceivedBook() throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Books WHERE IsReceived = ?;");
        preparedStatement.setBoolean(1, false);
        return preparedStatement.executeQuery().next();
    }

    public boolean hasDonatedABookBefore(String donatorName) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Books WHERE DonatorName = ?;");
        preparedStatement.setString(1, donatorName);
        return preparedStatement.executeQuery().next();
    }

    public boolean hasReceivedABookBefore(String receiverName) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Books WHERE ReceiverName = ?;");
        preparedStatement.setString(1, receiverName);
        return preparedStatement.executeQuery().next();
    }

    public void receiveBook(Packages.ReceivePackage receivePackage) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Books SET IsReceived = ?, ReceiverName = ?" +
                "\nWHERE Name = ? AND DonatorName = ?;");

        preparedStatement.setBoolean(1, true);
        preparedStatement.setString(2, receivePackage.getReceiverName());
        preparedStatement.setString(3, receivePackage.getBookName());
        preparedStatement.setString(4, receivePackage.getDonatorName());
        preparedStatement.execute();
    }

    public void unReceiveBook(Packages.ReceivePackage receivePackage) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Books SET IsReceived = ?, ReceiverName = ?" +
                "\nWHERE Name = ? AND DonatorName = ? AND ReceiverName = ?;");

        preparedStatement.setBoolean(1, false);
        preparedStatement.setString(2, null);
        preparedStatement.setString(3, receivePackage.getBookName());
        preparedStatement.setString(4, receivePackage.getDonatorName());
        preparedStatement.setString(5, receivePackage.getReceiverName());
        preparedStatement.execute();
    }

    public ArrayList<Packages.BookPackage> getUnReceivedBooks() throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Books WHERE IsReceived = ?;");
        preparedStatement.setBoolean(1, false);

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Packages.BookPackage> bookPackages = new ArrayList<Packages.BookPackage>();

        while(resultSet.next())
            bookPackages.add(turnBookRecordIntoBookPackage(resultSet));

        return bookPackages;
    }

    public ArrayList<Packages.BookPackage> getDonatedBooksByPerson(String donatorName) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Books WHERE DonatorName = ?");
        preparedStatement.setString(1, donatorName);

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Packages.BookPackage> bookPackages = new ArrayList<Packages.BookPackage>();

        while (resultSet.next())
            bookPackages.add(turnBookRecordIntoBookPackage(resultSet));

        return bookPackages;
    }

    public ArrayList<Packages.BookPackage> getReceivedBooksByPerson(String receiverName) throws SQLException {
        Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Books WHERE ReceiverName = ?");
        preparedStatement.setString(1, receiverName);

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Packages.BookPackage> bookPackages = new ArrayList<Packages.BookPackage>();

        while (resultSet.next())
            bookPackages.add(turnBookRecordIntoBookPackage(resultSet));

        return bookPackages;
    }

    private Packages.BookPackage turnBookRecordIntoBookPackage(ResultSet bookResultSet) throws SQLException {
        Packages.BookPackage bookPackage = new Packages.BookPackage();
        bookPackage.setName(bookResultSet.getString("Name"));
        bookPackage.setReceived(bookResultSet.getBoolean("IsReceived"));
        bookPackage.setDonatorName(bookResultSet.getString("DonatorName"));
        bookPackage.setReceiverName(bookResultSet.getString("ReceiverName"));
        return bookPackage;
    }
}
