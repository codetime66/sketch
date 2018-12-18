import java.util.*;

class Main {
    public static void main(String[] args) {
        // Non-existent bundle.
        try {
            ResourceBundle rb = ResourceBundle.getBundle("foo");
        } catch (MissingResourceException e) {
            System.out.println(e.getMessage());     
                                        // can't find resource for foo_en_US
            System.out.println(e.getClassName());   // foo_en_US
            System.out.println("*"+e.getKey()+"*"); // **
        }

        // Non-existent resource.
        try {
            ResourceBundle rb = ResourceBundle.getBundle("data");
            rb.getString("foo");
        } catch (MissingResourceException e) {
            System.out.println(e.getMessage());     // Can't find resource
            System.out.println(e.getClassName());   
                                        // java.util.PropertyResourceBundle
            System.out.println("*"+e.getKey()+"*"); // *foo*
        }
    }
}
