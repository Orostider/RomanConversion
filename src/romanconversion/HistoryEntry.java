package romanconversion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Hege
 */
//A data class for the entries inserted to HistoryEntry singleton
public class HistoryEntry {

    private String romNum;
    private int converted;
    private String stamp;

    public HistoryEntry(String romanNum, int result) {
        setRomNum(romanNum);
        setConverted(result);
        setStamp(stamp()); //Upon creation, stamp() creates a timestamp and assigns it. 
    }

    private void setRomNum(String romNum) {
        this.romNum = romNum;
    }

    private void setConverted(int converted) {
        this.converted = converted;
    }

    private String stamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
        String stampString = sdf.format(new Date());
        return stampString;
    }

    private void setStamp(String st) {
        this.stamp = st;
    }

    public String printEntry() {
        return stamp + " " + romNum + " = " + converted;
    }

}
