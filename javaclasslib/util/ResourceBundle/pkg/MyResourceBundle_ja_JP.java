package pkg;

import java.util.*;

public class MyResourceBundle_ja_JP extends ResourceBundle {
    public Object handleGetObject(String key) {
        if (key.equals("food")) {
            return new String[]{"sushi", "udon", "tempura"};
        }
        return null;
    }

    public Enumeration getKeys() {
        if (parent != null) {
            return parent.getKeys();
        }
        return null;
    }
}
