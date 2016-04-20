package romanconversion;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Hege
 */
//Converter class handles the actual calculation
public class Converter {

    private final ConversionHistory ch;
    private Stack<String> stack;
    private final List<String> allowed;
    private final String[] romans = {"I", "V", "X", "L", "C", "D", "M"};

    //Boolean values indicating whether a certain numeral has already been met
    private boolean iMet = false;
    private boolean vMet = false;
    private boolean xMet = false;
    private boolean lMet = false;
    private boolean cMet = false;
    private boolean dMet = false;
    private boolean mMet = false;

    /*
        ConversionHistory is a singleton, so the contructor has to fetch the 
        intance through a getter.
        The stack is created to handle the letters representing roman numerals.
        The list of allowed characters is created to prevent illegal numerals
     */
    public Converter() {
        this.ch = ConversionHistory.getInstance();
        stack = new Stack();
        allowed = Arrays.asList(romans);
    }

    public String printHistory() {
        return ch.printHistory();
    }

    public String convert(String rom) throws InvalidInput {

        //The input is first tried to add to the stack. In case of an exception
        //null is returned to indicate a failed conversion. 
        try {
            addRoman(rom);
        } catch (InvalidInput i) {
            System.out.println(i.getMessage());
            return null;
        }

        /*
            The converting operation starts by iterating through the stack and checking
            the conditions. The operation checks if too large numerals have already been met
            or if a certain numeral's frequency is too high. 
            If illegal conditions aren't found, legal conditions are checked and the calculation is done.
         */
        int result = 0;
        System.out.println("Stack height:" + stack.height());
        for (int i = stack.height(); i > 0; i--) {
            String peek = stack.peek();
            int freq = stack.countFreq(peek);
            String popd = stack.pop();
            switch (popd) {
                case "I":
                    if ((iMet && (xMet && vMet)) || lMet || cMet || dMet || mMet || (freq > 4) || stack.fourStraight(peek)) {
                        stack.clear();
                        throw new InvalidInput("Invalid syntax. i");
                    } else if ((!vMet && !xMet)) {
                        result += 1;
                        iMet = true;
                    } else if ((xMet && !vMet) || (iMet && !xMet) || (!iMet && vMet)) {
                        result -= 1;
                        iMet = true;
                    } else {
                        System.out.println("I problem");
                    }
                    break;
                case "V":
                    if ((vMet && (lMet && xMet)) || cMet || dMet || mMet || (freq > 4) || stack.fourStraight(peek)) {
                        stack.clear();
                        throw new InvalidInput("Invalid syntax. v");
                    } else if ((!xMet && !lMet)) {
                        result += 5;
                        vMet = true;
                    } else if ((lMet && !xMet) || (vMet && !lMet) || (!vMet && xMet)) {
                        result -= 5;
                        vMet = true;
                    } else {
                        System.out.println("V problem");
                    }
                    break;
                case "X":
                    if ((xMet && (cMet && lMet)) || dMet || mMet || (freq > 4) || stack.fourStraight(peek)) {
                        stack.clear();
                        throw new InvalidInput("Invalid syntax. x");
                    } else if ((!lMet && !cMet)) {
                        result += 10;
                        xMet = true;
                    } else if ((cMet && !lMet) || (xMet && !cMet) || (!xMet && lMet)) {
                        result -= 10;
                        xMet = true;
                    } else {
                        System.out.println("X problem");
                    }
                    break;
                case "L":
                    if ((lMet && (dMet && cMet)) || mMet || (freq > 4) || stack.fourStraight(peek)) {
                        stack.clear();
                        throw new InvalidInput("Invalid syntax. l");
                    } else if ((!cMet && !cMet)) {
                        result += 50;
                        lMet = true;
                    } else if ((dMet && !cMet) || (lMet && !dMet) || (!lMet && cMet)) {
                        result -= 50;
                        lMet = true;
                    } else {
                        System.out.println("L problem");
                    }
                    break;
                case "C":
                    if ((cMet && (mMet && dMet)) || (freq > 4) || stack.fourStraight(peek)) {
                        stack.clear();
                        throw new InvalidInput("Invalid syntax. c");
                    } else if ((!dMet && !mMet)) {
                        result += 100;
                        cMet = true;
                    } else if ((mMet && !dMet) || (cMet && !mMet) || (!cMet && dMet)) {
                        result -= 100;
                        cMet = true;
                    } else {
                        System.out.println("C problem");
                    }
                    break;
                case "D":
                    if ((dMet && (mMet)) || (freq > 4) || stack.fourStraight(peek)) {
                        stack.clear();
                        throw new InvalidInput("Invalid syntax. d");
                    } else if ((!mMet)) {
                        result += 500;
                        dMet = true;
                    } else if (mMet) {
                        result -= 500;
                        dMet = true;
                    } else {
                        System.out.println("D problem");
                    }
                    break;
                case "M":
                    if (mMet && (freq > 4) || stack.fourStraight(peek)) {
                        stack.clear();
                        throw new InvalidInput("Invalid syntax. m");
                    } else if ((!mMet)) {
                        result += 1000;
                        mMet = true;
                    } else if (mMet) {
                        result += 1000;
                        mMet = true;
                    } else {
                        System.out.println("M problem");
                    }
                    break;

            }
        }

        //After the calculation an entry is added to the history and both the stack
        //and the boolean values are reset.
        ch.addEntry(new HistoryEntry(rom, result));
        stack.clear();
        clearMets();
        return Integer.toString(result);
    }

    //addRoman works both as a legitimacy checker and as the stack handler
    private void addRoman(String rom) throws InvalidInput {
        List<String> nums = Arrays.asList(rom.split("(?!^)"));
        for (String s : nums) {
            if (!allowed.contains(s)) {
                stack.clear();
                throw new InvalidInput("Invalid roman numeral: " + s);
            } else {
                stack.push(s);
                System.out.println("Pushed " + s);
            }
        }
    }

    private void clearMets() {
        iMet = false;
        vMet = false;
        xMet = false;
        lMet = false;
        cMet = false;
        dMet = false;
        mMet = false;
    }

}
