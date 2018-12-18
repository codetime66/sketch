import java.util.*;
import java.text.*;

class Main {
    static void print(String name, Locale locale) {
        System.out.print(name);
        System.out.print(" " + locale.getDisplayName());

        System.out.print(" " + locale.getDisplayLanguage());
        System.out.print(" " + locale.getLanguage());
        System.out.print(" " + locale.getISO3Language());
        System.out.print(" " + locale.getDisplayCountry());
        System.out.print(" " + locale.getCountry());
        System.out.print(" " + locale.getISO3Country());
        System.out.print(" " + locale.getDisplayVariant());
        System.out.print(" " + locale.getVariant());

        System.out.println(" " + locale.toString());
    }

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        //Locale.setDefault(Locale.ITALIAN);
        print("CANADA", Locale.CANADA);
        print("CANADA_FRENCH", Locale.CANADA_FRENCH);
        print("CHINA", Locale.CHINA);
        print("CHINESE", Locale.CHINESE);
        print("ENGLISH", Locale.ENGLISH);
        print("FRANCE", Locale.FRANCE);
        print("FRENCH", Locale.FRENCH);
        print("GERMAN", Locale.GERMAN);
        print("GERMANY", Locale.GERMANY);
        print("ITALIAN", Locale.ITALIAN);
        print("ITALY", Locale.ITALY);
        print("JAPAN", Locale.JAPAN);
        print("JAPANESE", Locale.JAPANESE);
        print("KOREA", Locale.KOREA);
        print("KOREAN", Locale.KOREAN);
        print("RPC", Locale.PRC);
        print("SIMPLIFIED_CHINESE", Locale.SIMPLIFIED_CHINESE);
        print("TAIWAN", Locale.TAIWAN);
        print("TRADITIONAL_CHINESE", Locale.TRADITIONAL_CHINESE);
        print("UK", Locale.UK);
        print("US", Locale.US);

        Locale[] locales = NumberFormat.getAvailableLocales();
        for (int i=0; i<locales.length; i++) {
            print("", locales[i]);
        }
    }
}