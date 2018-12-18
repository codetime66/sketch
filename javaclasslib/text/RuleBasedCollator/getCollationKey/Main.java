import java.text.Collator;
import java.text.CollationKey;
import java.util.Locale;

class Main {
    public static void main(String args[]) {
        Locale loc = Locale.FRENCH;

        // Create an instance of a subclass of collator.
        Collator collator = Collator.getInstance(loc);

        // Find out what kind of subclass it is.
        System.out.println(collator.getClass());
                                     // java.text.RuleBasedcollator

        // Set up strings to sort.
        String s1 = "ABC";
        String s2 = "abc";
        String tmp;
        String[] array = new String[] { s1, s2 };

        // Create collation keys from the two strings.
        CollationKey ck1 = collator.getCollationKey(s1);
        CollationKey ck2 = collator.getCollationKey(s2);

        // Compare two strings in the array and sort them.
        if( ck1.compareTo(ck2) > 0 ) {
            tmp = array[0];
            array[0] = array[1];
            array[1] = tmp;
        }
        printArray(array);
    }

    // Print the contents of a 1-dimensional array
    static void printArray(Object[] a) {
        for (int i=0; i<a.length; i++) {
            if (a[i].toString().length() == 0) {
                System.out.print("(empty)" + "  ");
            } else {
                System.out.println(a[i] + "  ");
            }
        }
    }
}
