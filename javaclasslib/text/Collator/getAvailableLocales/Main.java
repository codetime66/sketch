import java.text.Collator;
import java.util.Locale;

class Main {
    public static void main(String args[]) {

        // Get list of installed locales with collators.
        Locale locales[] = Collator.getAvailableLocales();

        // Print all locales.
        for (int i = 0; i < locales.length; i++) {
            System.out.println(locales[i].getDisplayName());
        }
    }
}
