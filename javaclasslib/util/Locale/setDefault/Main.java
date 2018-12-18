import java.util.*;

class Main {
    public static void main(String[] args) {
        // Get and print the real default.
        Locale locale = Locale.getDefault();
        System.out.println(locale.toString());              // en_US

        // Create a new locale.
        locale = new Locale("ab", "XY", "Solaris");

        // Make it the default.
        Locale.setDefault(locale);

        // Print the new default.
        System.out.println(locale.toString());              // ab_XY_Solaris
    }
}