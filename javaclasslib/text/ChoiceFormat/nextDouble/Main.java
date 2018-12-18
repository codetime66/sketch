import java.text.ChoiceFormat;

class Main {
    static public void main(String[] args) {

        // Define the integer ranges.
        double[] limits = {Double.NEGATIVE_INFINITY,
                           1.0,
                           ChoiceFormat.nextDouble(1.0)}; 

        // Define the strings that map to the ranges.
        String[] strings = {"Less than one","One","Greater than one"}; 

        // Create a choice format based on the limits and dayName arrays.
        ChoiceFormat form = new ChoiceFormat(limits, strings); 

        // Diagnostics - print the pattern and nextDouble
        System.out.println("pattern: " + form.toPattern());
        System.out.println("nextDouble(1.0): " + ChoiceFormat.nextDouble(1.0));

        // Print a heading.
        System.out.println("\n" + "num   format(num)");  
        System.out.println("---   -------------");  

        // Loop over the range, formatting and parsing.
        for (double num = 0.5; num <= 1.5; num = num + .5) {

            System.out.print(num + "   ");

            // Format the number and print the resulting string.
            System.out.println(form.format(num)); 
        }
    }
}
