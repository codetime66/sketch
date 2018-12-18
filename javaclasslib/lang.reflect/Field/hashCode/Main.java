import java.awt.*;
import java.util.*;
import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        try {
            Field f = Point.class.getField("x");
            ht.put(f, f);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
