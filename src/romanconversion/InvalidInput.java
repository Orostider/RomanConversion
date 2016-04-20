package romanconversion;

/**
 *
 * @author Hege
 */
//This exception is created to allow custom exception throwing in the calculator
public class InvalidInput extends Exception {

    public InvalidInput(String msg) {
        super(msg);
    }
}
