import java.text.MessageFormat;
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

        // Create a string buffer for the format method result.
        StringBuffer strbuf = new StringBuffer("");

        // Get the pattern from resource bundle and apply it to mf.
        mf.applyPattern(myResources.getString("pattern"));

        // Format the objects in the arguments array to the string buffer.
        StringBuffer result = mf.format(arguments, strbuf, null);

        // Print the formatted string.
        System.out.println(result);
    }
}
