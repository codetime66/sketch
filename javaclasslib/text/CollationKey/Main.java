import java.text.CollationKey;
import java.text.Collator;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale loc = Locale.FRENCH;

        // Create an instance of a subclass of collator.
        Collator myCollator = Collator.getInstance(loc);

        // Create array of collation keys for strings to be sorted. 
        CollationKey[] keys = new CollationKey[3];
        keys[0] = myCollator.getCollationKey("Tom"); 
        keys[1] = myCollator.getCollationKey("Dick"); 
        keys[2] = myCollator.getCollationKey("Harry"); 

        // Sort keys and print strings.
        sortArray(keys); 
        printArray(keys);             // "Dick" "Harry" "Tom"
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
