import java.awt.*;
import java.util.*;
import java.text.*;

class Main extends Frame {
    static int COLWIDTH = 10;

    // Create text area
    TextArea tArea = 
        new TextArea("", 49, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);

    Main(String s) {
        super("Collator Example");

        // Initialize various objects with the text.
        tArea.setText(s);
        tArea.setEditable(false);
        tArea.setFont(new Font("Courier", Font.PLAIN, 12));

        // Add layout to text area and listen for key events.
        add(tArea, BorderLayout.CENTER);

        setSize(400, 400);
        pack();
        show();
    }

    public static void main(String[] args) {
        Locale loc = Locale.FRENCH;

        // Create an instance of a subclass of collator.
        Collator collator = Collator.getInstance(loc);

        int[] decompositions = { 
                                  Collator.NO_DECOMPOSITION,
                                  Collator.CANONICAL_DECOMPOSITION,
                                  Collator.FULL_DECOMPOSITION
                               };

        // Set up strings to sort.
        String[] array = new String[] { 
                   "ábc",
                   "Ábc",
                   "àbc",
                   "Abc",
                   "äbc",
                   "Àbc",
                   "abc",
                   "Äbc",
            //     "A\u0300bc",    // A grave b c
                   "\u00b5",       // µ - MICRON SIGN
                   "\u0041",       // A - LATIN CAPITAL LETTER A
                   "\u30ab",       // ? - KATAKANA LETTER KA
                   "\u03bc",       // µ - GREEK SMALL LETTER MU
                   "\uff21",       // A - FULLWIDTH CAPITAL LETTER A
                   "\uff76"        // ? - HALFWIDTH KATAKANA LETTER KA
        };

        // Set up strings to sort.
        String[] arrayLabel = new String[] { 
                   "a-acute b c",
                   "A-acute b c",
                   "a-grave b c",
                   "A b c",
                   "a-umlaut b c",
                   "A-grave b c",
                   "a b c",
                   "A-umlaut b c",
            //     "A\\u0300bc -  A grave b c",
                   "\\u00b5 - MICRON SIGN",
                   "\\u0041 - LATIN CAPITAL LETTER A",
                   "\\u30ab - KATAKANA LETTER KA",
                   "\\u03bc - GREEK SMALL LETTER MU",
                   "\\uff21 - FULLWIDTH CAPITAL LETTER A",
                   "\\uff76 - HALFWIDTH KATAKANA LETTER KA"
        };

        StringBuffer strbuf = new StringBuffer("");

        for (int i=0; i < decompositions.length; i++) {
            collator.setDecomposition(decompositions[i]);
            int decomp = collator.getDecomposition();

            switch (decomp) {
                case(0):
                    System.out.print("No Decomposition: ");
                    strbuf.append("No Decomposition: ");
                    System.out.println(" (Do not use this mode)");
                    strbuf.append(" (Do not use this mode)");
                    break;
                case(1):
                    System.out.print("Canonical Decomposition: ");
                    strbuf.append("Canonical Decomposition: ");
                    System.out.println(" ---------------------");
                    strbuf.append(" ---------------------");
                    break;
                case(2):
                    System.out.print("Full Decomposition: ");
                    strbuf.append("Full Decomposition: ");
                    System.out.println(" --------------------------");
                    strbuf.append(" --------------------------");
                    break;
            }
            strbuf.append("\n");

            // Sort and append the strings to the string buffer.
            sortArray(collator, array, arrayLabel);
            appendArrayToStrBuf(array, arrayLabel, strbuf);
        }
        new Main(new String(strbuf));
    }

    // Sort strings.
    public static void sortArray(Collator collator, 
                                 String[] strArray,
                                 String[] strArrayLabel) {
        String tmp;
        // Sort the string array.
        for (int i = 0; i < strArray.length; i++) {
            for (int j = i + 1; j < strArray.length; j++) {
                // Compare members of the array two at a time.
                if( collator.compare(strArray[i], strArray[j] ) > 0 ) {
                    // Swap strArray[i] and strArray[j].
                    tmp = strArray[i];
                    strArray[i] = strArray[j];
                    strArray[j] = tmp;

                    // Swap the same items in the label array
                    tmp = strArrayLabel[i];
                    strArrayLabel[i] = strArrayLabel[j];
                    strArrayLabel[j] = tmp;
                }
            }
        }
    }

    // Print the contents of a 1-dimensional array of strings
    // plus its label array.
    static void appendArrayToStrBuf(String[] a,
                                    String[] aLabel,
                                    StringBuffer strbuf) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].length() == 0) {
                strbuf.append("(empty)" + "\n");
                System.out.println("(empty)");
            } else {
                if (a[i] == null) {
                    strbuf.append("(null)" + "\n");
                    System.out.println("(null)");
                } else {
                    strbuf.append(a[i] 
                                + appendSpaces(COLWIDTH - a[i].length()) 
                                + aLabel[i] + "\n");
                    System.out.println(a[i] 
                                + appendSpaces(COLWIDTH - a[i].length()) 
                                + aLabel[i]);
                }
            }
        }
        strbuf.append("\n");
        System.out.println();
    }

    // Print spaces.
    static String appendSpaces(int count) {
        String strTmp = "";
        for (int i = 0; i < count; i++) {
            strTmp = strTmp + " ";
        }
        return strTmp;
    }
}
