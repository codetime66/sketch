import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        try {
            Field f1 = Thread.class.getField("MIN_PRIORITY");
            Field f2 = java.awt.Point.class.getField("x");
    
            System.out.println(f1.toString());
            System.out.println(f2.toString());
    
            // The following two lines are equivalent to the above two lines.
            // System.out.println(f1);
            // System.out.println(f2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}