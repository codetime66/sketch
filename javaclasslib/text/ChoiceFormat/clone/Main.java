import java.text.ChoiceFormat;

class Main {
    public static void main(String args[]) {

        // Create choice format.
        ChoiceFormat cf = new ChoiceFormat("1.0#Yes|2.0#No"); 

        // Print pattern.
        System.out.println("Original pattern: " + cf.toPattern());

        // Create a clone.
        ChoiceFormat cfCopy = (ChoiceFormat)cf.clone(); 

        // Print pattern.
        System.out.println("Copy's pattern:   " + cf.toPattern());

        // Test for equality.
        if (cf.equals(cfCopy)) {
            System.out.println("Clone is equal to original");
        }

        // Compute hashcode.
        int hc = cf.hashCode();
        System.out.println("Hash code:        " + hc);
    }
}
