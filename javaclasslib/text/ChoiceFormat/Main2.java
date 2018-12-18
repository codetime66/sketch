import java.text.ChoiceFormat;
import java.text.ParsePosition;

class Main2 {
    static public void main(String[] args) {

        // Define the integer ranges.
        double[] limits = {1,2,3,4,5,6,7}; 

        // Define the strings that map to the ranges.
        String[] dayNames = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"}; 

        // Create a choice format based on the limits and dayName arrays.
        ChoiceFormat form = new ChoiceFormat(limits, dayNames); 

        // Diagnostics - print the pattern.
        System.out.println("Pattern: " + form.toPattern());

        // Create a parse position for parsing.
        ParsePosition pos = new ParsePosition(0); 

        // Print a heading.
        System.out.println("\n" + "Formatting | Parsing");  

        // Loop over the range, formatting and parsing.
        for (double num = 0.0; num <= 8.0; num = num + .5) {
            pos.setIndex(0);

            System.out.print(num);

            System.out.print("  ->   ");

            // Format the number and print the resulting string.
            System.out.print(form.format(num)); 

            // Print an arrow.
            System.out.print("  ->   "); 

            // Parse the formatted output back to a number.
            System.out.println(form.parse(form.format(num), pos)); 
        }
    }
}
