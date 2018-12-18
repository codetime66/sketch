import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        // Create a new boolean array.
        Object a = Array.newInstance(boolean.class, 7);

        // Set an element.
        Array.setBoolean(a, 2, Math.random() > .5);
        
        // Print the element.
        System.out.println(Array.getBoolean(a, 2));

        // Show that a is really an object of type boolean[]
        boolean[] b = (boolean[])a;
        Array.setBoolean(b, 3, Math.random() > .5);
        System.out.println(Array.getBoolean(b, 3));
    }
}