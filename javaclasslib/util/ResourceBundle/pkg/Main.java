package pkg;

import java.util.*;

class Main {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(
            "pkg.MyResourceBundle", Locale.FRENCH);

        // The French locale is not available so the default result 
        // bundle is used.
        // Print it to prove it.
        System.out.println(rb.getClass());           // MyResourceBundle

        rb = ResourceBundle.getBundle("pkg.MyResourceBundle", Locale.JAPAN);
        
        // The Japan locale is available so it is used.
        System.out.println(rb.getClass());           // MyResourceBundle_ja_JP

        // Print the available keys.
        for (Enumeration e=rb.getKeys(); e.hasMoreElements(); ) {
            System.out.println(e.nextElement());     // animal food version
        }

        System.out.println(rb.getStringArray("food")[0]);     // sushi
        System.out.println(rb.getString("animal"));           // bird
        System.out.println(rb.getObject("version"));          // 42

        // The following automatically creates a ResourceBundle object for a 
        // properties file.  The name of the properties file is data.properties.
        rb = ResourceBundle.getBundle("pkg.data", Locale.JAPAN);
        System.out.println(rb.getObject("p1"));               // data_ja
    }
}
