package pkg;

import java.util.*;

public class MyResourceBundle extends ResourceBundle {

    public Object handleGetObject(String key) {
        if (key.equals("animal")) {
            return "bear";
        } else if (key.equals("food")) {
            return new String[]{"hamburger", "pizza", "apple pie"};
        } else if (key.equals("version")) {
            return new Integer(42);
        }
        return null;
    }

    String[] keys = {"animal", "food", "version"};
    public Enumeration getKeys() {
        return new Enumeration() {
            int count;

            public boolean hasMoreElements() {
                return count < keys.length;
            }

            public Object nextElement() {
                if (count < keys.length) {
                    return keys[count++];
                }
                throw new NoSuchElementException("Enumeration");
            }
        };
    }
}
