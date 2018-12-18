import java.util.*;

class Main {
    public static void main(String[] args) {
        Locale l = new Locale("En", "Us", "Unix");
        System.out.println(l.getLanguage());    // en
        System.out.println(l.getCountry());     // US
        System.out.println(l.getVariant());     // UNIX
        System.out.println(l.toString());       // en_US_UNIX

        // Demonstrates a Java 1.1.4 bug where the variant is not displayed 
        // if the country code is empty.
        l = new Locale("En", "", "Unix");
        System.out.println(l.getLanguage());    // en
        System.out.println(l.getCountry());     // 
        System.out.println(l.getVariant());     // UNIX
        System.out.println(l.toString());       // en
    }
}