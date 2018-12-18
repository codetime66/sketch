/*
This example creates byte arrays from two CollationKey objects, 
then compares them with a byte-comparison method called compareBytes(). 
The method compareBytes(a.toByteArray(), b.toByteArray()) should always 
return the same result as a.compareTo(b).
*/

import java.text.CollationKey;
import java.text.Collator;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale loc = Locale.FRENCH;

        // Create an instance of a subclass of collator.
        Collator myCollator = Collator.getInstance(loc);

        // Create array of byte arrays from the strings to be sorted. 
        byte[][] byteArrays = new byte[3][];
        byteArrays[0] = myCollator.getCollationKey("Tom").toByteArray(); 
        byteArrays[1] = myCollator.getCollationKey("Dick").toByteArray(); 
        byteArrays[2] = myCollator.getCollationKey("Harry").toByteArray(); 

        // Sort keys and print strings.
        sortArray(byteArrays); 
//      printArray(byteArrays);             // "Dick" "Harry" "Tom"
    }

    // Sort bytes.
    public static void sortArray(byte[][] byteArrays) {
        byte[] tmp;

        // Sort the byteArrays by comparing two at a time.
        for (int i = 0; i < byteArrays.length; i++) {
            for (int j = i + 1; j < byteArrays.length; j++) {

                // Compare byteArrays
                if( compareBytes( byteArrays[i], byteArrays[j] ) > 0 ) {
                    //swap byteArrays[i] and byteArrays[j] 
                    tmp = byteArrays[i];
                    byteArrays[i] = byteArrays[j];
                    byteArrays[j] = tmp;
                }
            }
        }
    }

// NEED TO DETERMINE HOW TO GET THE SOURCE STRING FROM THE BYTEARRAYS
// IN OTHER WORDS, ONCE I'VE SORTED THE BYTEARRAYS, HOW DO I PRINTOUT
// THE ORIGINAL SOURCE STRINGS IN THAT ORDER?
// One possibility is adding either the string or an ID number to the
// array of byte arrays.

    // Print contents of an array
    static void printArray(byte[][] a) {
        for (int i = 0; i < a.length; i++) {
  //          if (a[i].getSourceString().length() == 0) {
  //              System.out.println("(empty)" + "  ");
  //          } else {
            for (int j = 0; j < a[i].length; i++) {
                System.out.println(a[i][j] + "  ");
            }
  //          }
        }
    }

    static int compareBytes(byte[] byteArray1, byte[] byteArray2) {
        int len = byteArray1.length;

        // Find the longer of the two arrays
        if (len > byteArray2.length) len = byteArray2.length;
        for (int i = 0; i < len; ++i) {
            if (byteArray1[i] == byteArray2[i]) continue;
            if (byteArray1[i] < byteArray2[i]) return -1;
            if (byteArray1[i] > byteArray2[i]) return 1;
        }
        if (byteArray1.length < byteArray2.length) return -1;
        if (byteArray1.length > byteArray2.length) return 1;
        return 0;
    }
}

