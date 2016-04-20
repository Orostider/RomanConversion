package romanconversion;

/**
 *
 * @author Hege
 * @param <T>
 */
//The stack is created by using generics, in order to enable its usage to other
//purposes. The stack works as a first in last out list. 
public class Stack<T extends Comparable<T>> {

    private static final int maxNum = 10; //Maximum size of the stack.
    private int amountOfObjects; //How many objects are stored in a stack
    private int top; //The top stack position
    private T[] array; //The array storing the objects

    //The top position is set to -1 to allow the first object to be at index 0
    public Stack() {
        amountOfObjects = 0;
        top = -1;
        array = (T[]) new Comparable[maxNum];
    }

    //An object is pushed into the stack. If the stack is full, the object isn't
    //added. If the stack isn't full, the top position moves and the object is set
    //to top index. 
    public boolean push(T item) {
        if (top >= maxNum - 1) {
            return false;
        } else {
            top++;
            array[top] = item;
            amountOfObjects++;
            //System.out.println(top);
            return true;
        }
    }

    //Pop returns the top object and erases it.
    public T pop() {
        if (top == -1) {
            return null;
        } else {
            amountOfObjects--;
            return array[top--];
        }
    }

    //Clears the array and set top and amountOfObjects to default
    public void clear() {
        for (int i = 0; i <= array.length - 1; i++) {
            array[i] = null;
        }
        top = -1;
        amountOfObjects = 0;
    }

    public String print() {
        String s = "";
        for (int i = top; i >= 0; i--) {
            s += array[i].toString() + "\n";
        }
        return s;
    }

    public int height() {
        return amountOfObjects;
    }

    //Counts the total frequency of the item
    public int countFreq(T item) {
        int frequency = 0;
        for (int i = 0; i <= amountOfObjects - 1; i++) {
            if (array[i].equals(item)) {
                frequency++;
            }
        }

        return frequency;
    }

    //Checks if an item forms a row of four
    public boolean fourStraight(T item) {
        int am = 0;
        for (int i = 0; i < amountOfObjects - 1; i++) {
            if (array[i].equals(item)) {
                am++;
                if (am == 4) {
                    return true;
                }
            } else {
                am = 0;
            }
        }
        return false;
    }

    //Returns the top object without removing it. 
    public T peek() {
        return array[top];
    }
}
