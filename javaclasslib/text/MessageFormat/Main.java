import java.text.MessageFormat;
import java.util.*;

class Main {
    static public void main(String[] args) {

        Locale[] locArray = {Locale.ENGLISH, Locale.FRENCH};

        for (int i = 0; i < locArray.length; ++i) {

            Locale loc = locArray[i];

            // Create a message format and set its locale.
            MessageFormat mf = new MessageFormat("");
            mf.setLocale(loc);

            // Diagnostics.
            System.out.println(mf.getLocale());

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

            // Diagnostics.
            System.out.println(mf.toPattern());

            // Format the objects in the arguments array to a string.
            String result = mf.format(arguments);

            // Print the formatted string.
            System.out.println(result);
            System.out.println("");       // Blank line
        }
    }
}
