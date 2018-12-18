import java.awt.*;
import java.util.*;
import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        try {
            Method f = Method.class.getMethod("hashCode", new Class[] {});
            ht.put(f, f);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
