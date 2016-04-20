package romanconversion;

import java.util.ArrayList;

/**
 *
 * @author Hege
 */

//A singleton class to store the conversion results. The class is a singleton
//to allow multiple sources to add entries to the same list. 
public class ConversionHistory {

    private ArrayList<HistoryEntry> entries = new ArrayList<>();
    private static ConversionHistory instance = new ConversionHistory();

    private ConversionHistory() {
    }

    public static ConversionHistory getInstance() {
        return instance;
    }

    public void addEntry(HistoryEntry e) {
        entries.add(e);
    }

    public String printHistory() {
        String s = "History:\n";
        for (HistoryEntry e : entries) {
            s += e.printEntry() + "\n";
        }
        return s;
    }
}
