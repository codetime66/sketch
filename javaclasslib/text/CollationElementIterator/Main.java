import java.text.CollationElementIterator;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale loc = Locale.FRENCH;
        int element;

        // Set up string to iterate over.
        // String str = "abc‡‚‰ÈËÍÎÓÔÙˆ˘˚¸Á¡¿ƒ…» ÀŒœ‘÷Ÿ⁄‹«";
        String str = "abcABC‡¿";
        System.out.println("String: " + str + "\n");

        // Create an instance of a subclass of collator.
        Collator collator = Collator.getInstance(loc);

        if (collator instanceof RuleBasedCollator) {

            // Cast to the subclass.
            RuleBasedCollator rbc = (RuleBasedCollator)collator;

            // Try changing the decomposition mode.
            // rbc.setDecomposition(Collator.NO_DECOMPOSITION);

            // Get the first key of the string.
            CollationElementIterator cei = 
                rbc.getCollationElementIterator(str);

            System.out.println("collation  primary  secondary  tertiary");
            System.out.println(" element    order     order      order");
            System.out.println("  (hex)");

            // Iterate to next character and get collation element.
            while ((element = cei.next()) != 
                    CollationElementIterator.NULLORDER) {

                System.out.print(" ");
                printInColumn(Integer.toHexString(element), 12);

                // Print the primary, secondary and tertiary orders.
                printInColumn(
                    CollationElementIterator.primaryOrder(element), 11
                );

                printInColumn(
                    CollationElementIterator.secondaryOrder(element), 11
                );

                printInColumn(
                    CollationElementIterator.tertiaryOrder(element), 0
                );
                System.out.println();
            }
        }
    }

    // Print string in a particular vertical column
    static void printInColumn(String str, int col) {
        System.out.print(str);
        for (int p = str.length(); p < col; ++p) {
            System.out.print(" ");
        }
    }

    // Print integer in a particular vertical column
    static void printInColumn(int integer, int col) {
        System.out.print(integer);
        for (int p = Integer.toString(integer).length(); p < col; ++p) {
            System.out.print(" ");
        }
    }
}
