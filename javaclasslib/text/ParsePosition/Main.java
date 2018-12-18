import java.text.*;
import java.io.*;
import java.util.*;

class Main {

     // Create a parse position object for tracking the parse.
     static ParsePosition parsePos = new ParsePosition(0);

     // Create three number formats.
     static NumberFormat decimalForm  = NumberFormat.getInstance();
     static NumberFormat currencyForm = NumberFormat.getCurrencyInstance();
     static NumberFormat percentForm  = NumberFormat.getPercentInstance();

     // Createfour date formats.
     static DateFormat shortDateForm  = 
           DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
     static DateFormat medDateForm = 
           DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
     static DateFormat longDateForm = 
           DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
     static DateFormat fullDateForm = 
           DateFormat.getDateInstance(DateFormat.FULL, Locale.US);

    Main (String str) {
        // Print out original text.
        System.out.println(str);

        // Parse through text for numbers.
        System.out.println("All Decimals Numbers ");
        parseNum(str, ".");
        System.out.println("\nCurrency ");
        parseNum(str, "$");
        System.out.println("\nPercent ");
        parseNum(str, "%");

        // Parse through text for dates.
        System.out.println("\nShort Date");
        parseNum(str, "short");
        System.out.println("\nMedium Date");
        parseNum(str, "med");
        System.out.println("\nLong Date");
        parseNum(str, "long");
        System.out.println("\nFull Date");
        parseNum(str, "full");
    }

    static void parseNum(String str, String t) {
        int beginParseIndex;
        int endParseIndex;
        Number num = null;
        Date date = null;

        while (parsePos.getIndex() < str.length()) {

            beginParseIndex = parsePos.getIndex();

            // Parse the string starting at parsePos.
            if (t.equals(".")) {
                // Find decimal number beginning at parsePos.
                num = decimalForm.parse(str, parsePos);
            } else if (t.equals("$")) {
                // Find currency beginning at parsePos.
                num = currencyForm.parse(str, parsePos);
            } else if (t.equals("%")) {
                // Find percentage beginning at parsePos.
                num = percentForm.parse(str, parsePos);
            } else if (t.equals("short")) {
                // Find short-style date beginning at parsePos.
                date = shortDateForm.parse(str, parsePos);
            } else if (t.equals("med")) {
                // Find medium-style date beginning at parsePos.
                date = medDateForm.parse(str, parsePos);
            } else if (t.equals("long")) {
                // Find long-style date beginning at parsePos.
                date = longDateForm.parse(str, parsePos);
            } else if (t.equals("full")) {
                // Find full-style date beginning at parsePos.
                date = fullDateForm.parse(str, parsePos);
            } else {
                System.err.println("Error: Bad character.");
                System.exit(1);
            }
            endParseIndex = parsePos.getIndex();

            if (num != null) {
                System.out.print("Index: " + beginParseIndex + "  ");
                System.out.println("Number: " + num);
            }

            if (date != null) {
                System.out.print("Index: " + beginParseIndex + "  ");
                System.out.println("Date:   " + date);
            }
            parsePos.setIndex(endParseIndex + 1);
        }
        // Reset parse position to 0 for next loop.
        parsePos.setIndex(0);
    }

    public static void main(String[] args) {

        // Accept an input text file
        if (args.length != 1) {
            System.err.println("This program parses thru text for numbers");
            System.err.println("Usage: java Main <filename>");
            System.exit(1);
        }
        try {
            // Read the text into a buffered reader
            // with '\n' for newlines
            BufferedReader rd = new BufferedReader(new FileReader(args[0]));
            String line;
            StringBuffer sbuf = new StringBuffer();

            while ((line = rd.readLine()) != null) {
                sbuf.append(line);
                sbuf.append('\n');
            }
            rd.close();

            // Call the constructor Main with the input string
            new Main(new String(sbuf));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
