package pkg;

import java.util.*;

public class MyResourceBundle_ja extends ResourceBundle {
    public Object handleGetObject(String key) {
        if (key.equals("animal")) {
            return "bird";
        } else if (key.equals("food")) {
            return new String[]{"rice"};
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
