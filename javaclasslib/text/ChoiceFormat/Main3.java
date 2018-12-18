import java.text.*;
import java.util.*;

class Main3 {
    static public void main(String[] args) {

        Locale[] locArray = {Locale.ENGLISH, Locale.FRENCH};

        MessageFormat mf;

        for (int i = 0; i < locArray.length; ++i) {

            Locale loc = locArray[i];

            // Create a message format and set its locale.
            mf = new MessageFormat("");
            mf.setLocale(loc);

            // Create a resource bundle object and load the bundle.
            ResourceBundle myResources = null;
            myResources = ResourceBundle.getBundle("MyResources", loc);

            // Define the integer ranges
            double[] filelimits = {0,1,2}; 

            // Get locale-specific strings
            String[] fileparts = { myResources.getString("are no files"), 
                                   myResources.getString("is one file"), 
                                   myResources.getString("are X files") };

            // Create a choice format based on the filelimits and fileparts
            ChoiceFormat fileformat = new ChoiceFormat(filelimits, fileparts); 

            // Create an array of formats
            Format[] formats = {fileformat, null, NumberFormat.getInstance()}; 

            // Get the pattern from resource bundle:  "There {0} on {1}"
            String pattern = myResources.getString("pattern");

            // Apply the pattern and set the formats to mf.
            mf.applyPattern(pattern); 
            mf.setFormats(formats); 

            // Create an array of objects to be formatted
            Object[] arguments = {null, "MyDisk", null}; 

            // Loop over the range. formatting and printing
            for (int num = 0; num < 4; num++) { 
                arguments[0] = new Integer(num); 
                arguments[2] = arguments[0]; 
                System.out.println(mf.format(arguments)); 
            }
            System.out.println();
        }
    }
}
