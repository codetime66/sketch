import java.text.ChoiceFormat;

class Main {
    static public void main(String[] args) {

        // Create an empty choice format.
        ChoiceFormat form = new ChoiceFormat(""); 

        // Define the integer ranges.
        double[] limits = {1,2,3,4,5,6,7}; 

        // Define the strings that map to the ranges.
        String[] dayNames = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"}; 

        // Apply the limits and dayNames.
        form.setChoices(limits, dayNames);

        // Format the number 2.5
        double num = 2.5;

        // Format the number and print the result.
        System.out.print(form.format(num));          // "Mon"
    }
}
