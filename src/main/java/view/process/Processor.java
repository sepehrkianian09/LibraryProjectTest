package view.process;

import view.Menu;
import view.process.person.DonatorProcessor;
import view.process.person.IOAccountProcessor;
import view.process.person.ReceiverProcessor;
import view.process.person.UserProcessor;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Processor {
    static HashMap<String, Processor> processesHashMap;
    protected static Scanner scanner = Menu.scanner;
    protected HashMap<String, FunctioningOption> functionsHashMap;

    public static void initProcessHashMaps(){
        processesHashMap = new HashMap<String, Processor>();
        processesHashMap.put("MainMenuProcessor", MainMenuProcessor.getInstance());
        processesHashMap.put("IOAccountProcessor", IOAccountProcessor.getInstance());
        processesHashMap.put("DonatorProcessor", DonatorProcessor.getInstance());
        processesHashMap.put("ReceiverProcessor", ReceiverProcessor.getInstance());

    }

    public static Processor findProcessorWithName(String name){
        return processesHashMap.get(name);
    }

    public boolean isThereFunctionWithName(String name){
        return functionsHashMap.containsKey(name);
    }

    public String executeTheFunctionWithName(String name) {
            return functionsHashMap.get(name).doTheThing();
    }
}
