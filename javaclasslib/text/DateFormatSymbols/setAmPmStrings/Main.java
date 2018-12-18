import java.text.DateFormatSymbols;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Locale locale = Locale.US;

        // Create a new date format symbols for default locale.
        DateFormatSymbols dfs = new DateFormatSymbols();

        // Assign new arbitrary characters for symbols.

        System.out.println("AM-PM STRINGS:");
        dfs.setAmPmStrings(new String[] {"AM", "PM"});
        printArray(dfs.getAmPmStrings());

        System.out.println("ERAS:");
        dfs.setEras(new String[] {"BC", "ap. J.-C."});
        printArray(dfs.getEras());

        System.out.println("MONTHS:");
        dfs.setMonths(new String[]
                   {"janvier", "février", "mars", "avril", "mai", "juin",
                    "juillet", "août", "septembre", "octobre", "novembre", 
                    "décembre"});
        printArray(dfs.getMonths());

        System.out.println("SHORT MONTHS:");
        dfs.setShortMonths(new String[]
                        {"jan", "fév", "mar", "avr", "mai", "jun",
                         "jul", "aoû", "sep", "oct", "nov", "déc"});
        printArray(dfs.getShortMonths());

        System.out.println("WEEKDAYS:");
        dfs.setWeekdays(new String[]
                   {"dimanche", "lundi", "mardi", "mercredi",
                    "jeudi", "vendredi", "samedi"});
        printArray(dfs.getWeekdays());

        System.out.println("SHORT WEEKDAYS:");
        dfs.setShortWeekdays(new String[]
                   {"dim", "lun", "mar", "mer", "jeu", "ven", "sam"});
        printArray(dfs.getShortWeekdays());

        System.out.println("ZONE STRINGS:");
        dfs.setZoneStrings(new String[][] 
        {
            {"ECT", "Central European Standard Time", "CEST",
             "Central European Daylight Time", "CEDT", "Paris"}
        });
        printDeepArray(dfs.getZoneStrings());

        System.out.println("LOCAL PATTERN CHARS:");
        dfs.setLocalPatternChars("GanjkHmsSEDFwWxhKz");
        System.out.println(dfs.getLocalPatternChars() + "\n");
    }

    // Print the contents of a 1-dimensional array
    static void printArray(Object[] a) {
        for (int i=0; i<a.length; i++) {
            if (a[i].toString().length() == 0) {
                System.out.print("(empty)" + "  ");
            } else {
                System.out.print(a[i] + "  ");
            }
        }
        System.out.print("\n\n");
    }

    // Print the contents of a 2-dimensional array.
    static void printDeepArray(Object[][] a) {
        for (int i=0; i<a.length; i++) {
            for (int j=0; j<a[i].length; j++) {
                System.out.print(a[i][j] + "   ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
