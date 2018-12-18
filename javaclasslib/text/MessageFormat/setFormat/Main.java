import java.text.*;
import java.util.*;

class Main {
    static public void main(String[] args) {

        Locale loc = Locale.FRENCH;

        // Create a message format and set its locale.
        MessageFormat mf = new MessageFormat("");
        mf.setLocale(loc);

        // Create a resource bundle object and load the bundle.
        ResourceBundle myResources = null;
        myResources = ResourceBundle.getBundle("MyResources", loc);

        // Assign the first 4 objects from the bundle to an array.
        Object[] arguments = {   
            new Integer(7),
            new Long(System.currentTimeMillis()),
            myResources.getString("color"), 
            myResources.getString("animal"), 
        }; 

        // Get the pattern from resource bundle and apply it to mf.
        mf.applyPattern(myResources.getString("pattern"));

        // Set the formats.
        mf.setFormat(0, DateFormat.getTimeInstance(DateFormat.SHORT, loc));
        mf.setFormat(1, DateFormat.getDateInstance(DateFormat.SHORT, loc));
        mf.setFormat(4, getIntegerFormat(loc));

        // Create a string buffer for the format method result.
        StringBuffer strbuf = new StringBuffer("");

        // Print the pattern.
        System.out.println("Pattern:");
        System.out.println(mf.toPattern() + "\n");

        // Format the objects in the arguments array to the string buffer.
        StringBuffer result = mf.format(arguments, strbuf, null);

        // Print the formatted string.
        System.out.println("Formatted result:");
        System.out.println(result);
    }

    static NumberFormat getIntegerFormat(Locale locale) {
        NumberFormat temp = NumberFormat.getInstance(locale);
        if (temp instanceof DecimalFormat) {
            DecimalFormat temp2 = (DecimalFormat) temp;
            temp2.setMaximumFractionDigits(0);
            temp2.setDecimalSeparatorAlwaysShown(false);
            temp2.setParseIntegerOnly(true);
        }
        return temp;
    }
}
