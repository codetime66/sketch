import java.text.Collator;
import java.util.Locale;
import java.io.*;

class Main {

    Main (String[] array) {
        Locale loc = Locale.FRENCH;
        String tmp;

        // Create an instance of a subclass of collator.
        Collator collator = Collator.getInstance(loc);

        // Find out what kind of subclass it is.
        System.out.println(collator.getClass() + "\n");
                                     // java.text.RuleBasedcollator
        // Sort and print the array.
        sortArray(collator, array);
        printArray(array);
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

    // Print the contents of an array
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
    }

    public static void main(String[] args) {
        // Accept a string as an argument
        if (args.length != 1) {
            System.err.println("This program sorts lines in a file");
            System.err.println("Usage: java Main input.txt");
            System.exit(1);
        }

        try {
            // Read in the entire contents of the file.
            BufferedReader rd = new BufferedReader(new FileReader(args[0]));
            String line;

            // Create a temporary array of a big size.
            String[] tmpArray = new String[100];

            int i = 0;
            while ((line = rd.readLine()) != null) {
                tmpArray[i] = line;
                ++i;
            }
            rd.close();

            // Create a new array of the right size.
            String[] newArray = new String[i];
            System.arraycopy(tmpArray, 0, newArray, 0, i);

            // Call the constructor passing in the array.
            new Main(newArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
