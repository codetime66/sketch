import java.text.*;
import java.io.*;
import java.util.*;

class Main {

    static String QUOTE = "\"";

    // Create field position objects for dates.
    static FieldPosition dayPos = new FieldPosition(DateFormat.DATE_FIELD);
    static FieldPosition monthPos = new FieldPosition(DateFormat.MONTH_FIELD);
    static FieldPosition yearPos = new FieldPosition(DateFormat.YEAR_FIELD);

    // Create date formats.
    static DateFormat fullDateForm = 
           DateFormat.getDateInstance(DateFormat.FULL, Locale.US);

    public static void main(String[] args) {
        // Get the current date.
        Date date = new Date();

        // Create string buffer to hold date.
        StringBuffer strbuf = new StringBuffer("");

        // Format the date in full form
        fullDateForm.format(date, strbuf, dayPos);
        println("\nFull Date:     " + strbuf + "\n");

        System.out.print("Day:           ");
        printPositionValues(strbuf, dayPos);
        println("            (Bug)");
        strbuf.setLength(0);

        // Print the month
        System.out.print("Month:         ");
        fullDateForm.format(date, strbuf, monthPos);
        printPositionValues(strbuf, monthPos);
        println("");
        strbuf.setLength(0);

        // Print the year
        System.out.print("Year:          ");
        fullDateForm.format(date, strbuf, yearPos);
        printPositionValues(strbuf, yearPos);
        println("");
        strbuf.setLength(0);

        // Print the field constant values
        println("\nField contants");
        println("Day field:     " + dayPos.getField());         // 3
        println("Month field:   " + monthPos.getField());       // 2
        println("Year field:    " + yearPos.getField());        // 1

    }

    static String s; 

    static void printPositionValues(StringBuffer strbuf, FieldPosition pos) {
        s = ("(" + pos.getBeginIndex() + "," 
                           + pos.getEndIndex() + ")  ");
        print(s);
        printSpaces(10 - s.length());
        print(QUOTE + strbuf.toString().substring(pos.getBeginIndex(),
                         pos.getEndIndex()) + QUOTE);
    } 


    static void print(String s) {
        System.out.print(s);
    }

    static void println(String s) {
        System.out.println(s);
    }

    // Print spaces.
    static void printSpaces(int count) {
        for (int p = 0; p < count; ++p) {
            System.out.print(" ");
        }
    }
}
