import java.text.RuleBasedCollator;
import java.text.ParseException;

class Main {
    public static void main(String args[]) {

        // Create a simple collator rule.
        String rules = ("< a,A < b,B < c,C < d,D < e,E < f,F " +
                        "< g,G < h,H < i,I < j,J < k,K < l,L " +
                        "< m,M < n,N < o,O < p,P < q,Q < r,R " +
                        "< s,S < t,T < u,U < v,V < w,W < x,X " +
                        "< y,Y < z,Z");

        // Set up strings to sort.
        String s0 = "Linda";
        String s1 = "Doug";
        String s2 = "Cinnamon";
        String s3 = "Lady";
        String tmp;
        String[] array = new String[] { s0, s1, s2, s3 };

        // Create a rule-based collator.
        try {
            RuleBasedCollator rbc = new RuleBasedCollator(rules);

            // Print the rule string.
             System.out.println("Rules:" + "\n" + rbc.getRules());

            // Sort and print the array.
            sortArray(rbc, array);
            printArray(array);

        } catch (ParseException pe) {
            System.out.println("Parse exception for rules");
        }
    }

    // Sort strings.
    public static void sortArray(RuleBasedCollator collator, 
                                           String[] strArray) {
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
    static void printArray(Object[] a) {
        System.out.println("\n" + "Sorted array:");
        for (int i = 0; i < a.length; i++) {
            if (a[i].toString().length() == 0) {
                System.out.print("(empty)" + "  ");
            } else {
                System.out.println(a[i] + "  ");
            }
        }
    }
}
