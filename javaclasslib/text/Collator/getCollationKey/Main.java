import java.text.Collator;
import java.text.CollationKey;
import java.util.Locale;

class Main {
    public static void main(String args[]) {
        Locale loc = Locale.FRENCH;

        // Create an instance of a subclass of collator.
        Collator collator = Collator.getInstance(loc);

        // Find out what kind of subclass it is.
        System.out.println(collator.getClass() + "\n");
                                     // java.text.RuleBasedcollator

        // Create array of collation keys for the strings to be sorted. 
        CollationKey[] keys = new CollationKey[3];

        // Create collation keys from the two strings.
        keys[0] = collator.getCollationKey("Tom");
        keys[1] = collator.getCollationKey("Dick");
        keys[2] = collator.getCollationKey("Harry");

        // Sort and print the array of keys.
        sortArray(keys);
        printArray(keys);
    }

    // Sort collation keys.
    public static void sortArray(CollationKey[] keys) {
        CollationKey tmp;

        // Sort the keys by comparing two at a time.
        for (int i = 0; i < keys.length; i++) {
            for (int j = i + 1; j < keys.length; j++) {

                // Compare keys .
                if( keys[i].compareTo( keys[j] ) > 0 ) {
                    //swap keys[i] and keys[j] 
                    tmp = keys[i];
                    keys[i] = keys[j];
                    keys[j] = tmp;
                }
            }
        }
    }

    // Print contents of an array
    static void printArray(CollationKey[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].getSourceString().length() == 0) {
                System.out.println("(empty)" + "  ");
            } else {
                System.out.println(a[i].getSourceString() + "  ");
            }
        }
    }
}
