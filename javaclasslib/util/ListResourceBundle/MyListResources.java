import java.awt.*;
import java.util.*;

public class MyListResources extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
        {"key-1", "wombat",},
        {"key-2", new String[]{"apple", "blueberry", "cantaloupe",}},
        {"key-3", new Point(1, 2),},
    };

/*  
    // This is an alternate but equivalent way to create the content array.
    public Object[][] getContents() {
        Object[][] result = new Object[3][2];
        result[0][0] = "key-1";
        result[0][1] = "wombat";
        result[1][0] = "key-2";
        result[1][1] = new String[]{"apple", "blueberry", "cantaloupe",};
        result[2][0] = "key-3";
        result[2][1] = new Point(1, 2);
        return result;
    }
*/
}
