import java.text.RuleBasedCollator;
import java.text.ParseException;

class Main {
    public static void main(String args[]) {

        // Create a simple collator rule.
       String rules = "< a < b < c < d";

       // Create a rule-based collator.
       try {
            RuleBasedCollator rbc = new RuleBasedCollator(rules);

            // Create a clone.
            RuleBasedCollator rbcCopy = (RuleBasedCollator)rbc.clone(); 
            System.out.println("Copy's rules:" + "\n" + rbc.getRules());

            // Tests for equality.
            if (rbc.equals(rbcCopy)) {
                System.out.println("Clone is equal to original");
            }

            // Compute hashcode.
            int hc = rbc.hashCode();
            System.out.println("Hash code:        " + hc);

            } catch (ParseException pe) {
            System.out.println("Parse exception for rules");
        }
    }
}
