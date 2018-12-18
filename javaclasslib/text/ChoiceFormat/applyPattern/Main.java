import java.text.ChoiceFormat;

class Main {
    static public void main(String[] args) {

        // Create a choice format using that pattern.
        ChoiceFormat form = new ChoiceFormat("");

        // Create the pattern string.
        String pattern = 
            "1.0#Sun|2.0#Mon|3.0#Tue|4.0#Wed|5.0#Thu|6.0#Fri|7.0#Sat";

        // Apply the pattern.
        form.applyPattern(pattern);

        // Diagnostics - iterate over the arrays.
        for (int i = 0; i < form.getFormats().length; i++) {

            // Get the formats.
            System.out.print((form.getFormats())[i] + "  ");
 
            // Get the limits.
            System.out.println((form.getLimits())[i]);
        }

        // Format the number 2.5
        double num = 2.5;

        // Format the number and print the result.
        System.out.println("\n" + form.format(num));       // "Mon"
    }
}
