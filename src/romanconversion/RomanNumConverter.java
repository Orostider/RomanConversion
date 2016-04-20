package romanconversion;

import java.util.Scanner;

/**
 *
 * @author Hege
 */
public class RomanNumConverter {

    public static void main(String[] args) {
        Converter c = new Converter();
        Scanner s = new Scanner(System.in);
        boolean on = true;

        while (on) {
            System.out.println("Insert a roman numeral consisting of:\n"
                    + "I, V, X, L, C, D, M\n"
                    + "Insert P to print history.\n"
                    + "Insert Q to quit.");
            String command = s.next().toUpperCase();
            switch (command) {
                case "Q":
                    on = false;
                    break;
                case "P":
                    System.out.println(c.printHistory());
                    break;
                default:
                    try {
                        String result = c.convert(command);
                        if (result != null) {
                            System.out.println(command + " = " + result);
                        } else {
                            System.out.println("Invalid input. Could not calculate.");
                        }

                    } catch (InvalidInput i) {
                        System.out.println(i.getMessage());
                    }

            }
        }

    }

}
