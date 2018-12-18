import java.text.*;
import java.util.*;
import java.io.*;

class Main {

    Main(String str) {

        System.out.println("input.txt:" + "\n" + str);

        Locale loc = Locale.FRENCH;

        // Create a message format and set its locale.
        MessageFormat mf = new MessageFormat("");
        mf.setLocale(loc);

        // Create a resource bundle object and load the bundle.
        ResourceBundle myResources = null;
        myResources = ResourceBundle.getBundle("MyResources", loc);

        // Get the pattern from resource bundle and apply it to mf.
        mf.applyPattern(myResources.getString("pattern"));

        // Create an array of formats corresponding to the 
        // 5 variables in the pattern.
        Format[] formats = new Format[] {
            DateFormat.getTimeInstance(DateFormat.MEDIUM, loc),
            DateFormat.getDateInstance(DateFormat.MEDIUM, loc),
            null,
            null,
            getIntegerFormat(loc)
        };

        // Diagnostics.
        System.out.println("Pattern before applying formats: ");
        System.out.println(mf.toPattern() + "\n");

        // Set the formats for the pattern.
        mf.setFormats(formats);

        // Diagnostics.
        System.out.println("Pattern after applying formats: ");
        System.out.println(mf.toPattern() + "\n");

        // Parses the string to produced an array of objects.
        Object[] result = mf.parse(str, (new ParsePosition(0)));

        // Print the objects created from the parse.
        System.out.println("Objects resulting from parse: ");
        for (int i=0; (i<result.length) && (result[i] != null); i++) {
            printInColumn("result[" + i + "]: ", 12);
            printInColumn((result[i]).getClass(), 25);
            System.out.println(result[i]);
        }
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

    static void printInColumn(Object obj, int col) {
        System.out.print(obj);
        for (int p = obj.toString().length(); p < col; ++p) {
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        // Accept a string as an argument
        if (args.length != 1) {
            System.err.println("This program parses a string");
            System.err.println("Usage: java Main input.txt");
            System.exit(1);
        }

        try {
            // Convert line endings to '\n' (not really necessary)
            // Read in the entire contents of the file.
            BufferedReader rd = new BufferedReader(new FileReader(args[0]));
            String line;
            StringBuffer sbuf = new StringBuffer();

            while ((line = rd.readLine()) != null) {
                sbuf.append(line);
                sbuf.append('\n');
            }
            rd.close();
            new Main(new String(sbuf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
