package view;

import com.google.gson.GsonBuilder;
import view.process.Processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    protected String processorName;
    protected Processor processor;
    private ArrayList<String> options;
    private String name;
    private String parentMenuName;
    private boolean isThereParentMenu;

    public static Menu makeMenu(String menuName) {
        String json = "";

        try {
            json = Menu.getJsonFromDB(menuName);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        Menu menu = new GsonBuilder().setPrettyPrinting().create().fromJson(json, Menu.class);
        return menu;
    }

    public static String getJsonFromDB(String menuName) throws FileNotFoundException {
        File file = new File("menujsons\\" + menuName + ".json");
        Scanner myScanner = new Scanner(file);
        StringBuilder json = new StringBuilder();
        while (myScanner.hasNextLine()){
            json.append(myScanner.nextLine());
            json.append("\n");
        }
        json = new StringBuilder(json.substring(0, json.length() - 1));
        return json.toString();
    }

    public Menu show() {
        System.out.println(this.name + ":");

        if (this.isThereParentMenu)
            System.out.println("0. Back");
        else
            System.out.println("0. Exit");

        for (int i = 0; i < this.options.size(); i++)
            System.out.println("" + (i + 1) + ". " + options.get(i));

        if(Processor.getLoginStatus())
            System.out.println((options.size() + 1) + ". " + "Logout");

        return this.execute();
    }

    public Menu execute() {
        processor = Processor.findProcessorWithName(processorName);
        Menu nextMenu = this;
        int input = 0;

        try {
            input = Integer.parseInt(scanner.nextLine().trim());
            if (input > options.size() + 1 || (!Processor.getLoginStatus() && input == options.size() + 1) || input < 0)
                throw new InputIsBiggerThanExistingNumbers("input integer is invalid");
        } catch (NumberFormatException e) {
            System.out.println("please enter an integer");
        } catch (NullPointerException e) {
            System.out.println("please enter an integer");
        } catch (InputIsBiggerThanExistingNumbers e) {
            e.printStackTrace();
        }

        if (input == 0) {
            if(isThereParentMenu) {
                nextMenu = Menu.makeMenu(this.parentMenuName);
            } else {
                nextMenu = null;
            }
        } else if(Processor.getLoginStatus() && input == options.size() + 1) {
            nextMenu = makeMenu(Processor.logOut(name));
        } else {
            if (processor.isThereFunctionWithName(this.options.get(input - 1)))
                nextMenu = Menu.makeMenu(processor.executeTheFunctionWithName(this.options.get(input - 1)));
            else
                nextMenu = Menu.makeMenu(this.options.get(input - 1));
        }

        return nextMenu;
    }

    private class InputIsBiggerThanExistingNumbers extends Exception {
        public InputIsBiggerThanExistingNumbers(String message) {
            super(message);
        }
    }
}
