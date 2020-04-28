package view.process.person;

import com.google.gson.Gson;
import control.person.DonatorController;
import model.Packages;
import view.process.FunctioningOption;

public class DonatorProcessor extends UserProcessor {
    private static DonatorController donatorController = DonatorController.getInstance();
    private static DonatorProcessor donatorProcessorInstance = null;

    protected DonatorProcessor(){
        super();
        functionsHashMap.put("Get Donated Books", new FunctioningOption() {
            public String doTheThing() {
                return getDonatedBooks();
            }
        });
        functionsHashMap.put("Donate Book", new FunctioningOption() {
            public String doTheThing() {
                return donateBook();
            }
        });

    }

    public static DonatorProcessor getInstance(){
        if(donatorProcessorInstance == null)
            donatorProcessorInstance = new DonatorProcessor();

        return donatorProcessorInstance;
    }

    public String donateBook(){
        Packages.DonatePackage donatePackage = new Packages.DonatePackage();

        System.out.println("Please Enter the Book You Wanna Donate");
        donatePackage.setBookName(scanner.nextLine().trim());

        donatePackage.setDonatorName(DonatorController.getLoginUserName());
        System.out.println(donatorController.controlTheDonating(new Gson().toJson(donatePackage)));
        return "Donator Menu";
    }

    public String getDonatedBooks(){
        System.out.println(donatorController.controlGettingDonatedBooks(DonatorController.getLoginUserName()));
        return "Donator Menu";
    }

}
