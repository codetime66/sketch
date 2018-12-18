import java.text.BreakIterator;
import java.util.Locale;

class Main {
    public static void main(String args[]) {

        // get list of installed locales with break iterators
        Locale locales[] = BreakIterator.getAvailableLocales();

        // print all locales
        for (int i = 0; i < locales.length; i++) {
            System.out.println(locales[i].getDisplayName());
        }
    }
}
