import java.text.Collator;
import java.util.Locale;

class Main {
    public static void main(String args[]) {
        Locale loc = Locale.FRENCH;

        // Create an instance of a subclass of collator.
        Collator collator = Collator.getInstance(loc);

        int[] strengths = {  Collator.PRIMARY, 
                             Collator.SECONDARY,
                             Collator.TERTIARY
                          // Collator.IDENTICAL  // BUG: Throws exception
                          };

        // Set up strings to sort.
        String[] array = new String[] {
            "Äbc",  
            "äbc",  
            "Àbc",
            "àbc",
            "Ábc",  
            "ábc ", 
            "Abc",  
            "abc"  
        };

        for (int i=0; i < strengths.length; i++) {
            collator.setStrength(strengths[i]);
            System.out.print("Strength: ");
            System.out.println(collator.getStrength());

            // Sort and print the string array.
            sortArray(collator, array);
            printArray(array);
        }
    }

    // Sort strings.
    public static void sortArray(Collator collator, String[] strArray) {
        String tmp;
        // Sort the string array.
        for (int i = 0; i < strArray.length; i++) {
            for (int j = i + 1; j < strArray.length; j++) {
                // Compare members of the array two at a time.
                if( collator.compare(strArray[i], strArray[j] ) > 0 ) {
                    //swap strArray[i] and strArray[j] 
                    tmp = strArray[i];
                    strArray[i] = strArray[j];
                    strArray[j] = tmp;
                }
            }
        }
    }

    // Print the contents of a 1-dimensional array
    static void printArray(String[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].length() == 0) {
                System.out.println("(empty)" + "  ");
            } else {
                if (a[i] == null) {
                    System.out.println("(null)" + "  ");
                } else {
                    System.out.println(a[i] + "  ");
                }
            }
        }
        System.out.println();
    }
}
