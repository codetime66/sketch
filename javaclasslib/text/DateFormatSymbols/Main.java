import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.*;
import java.awt.*;

class Main extends Frame {
    TextArea textArea = 
        new TextArea("", 30, 100, TextArea.SCROLLBARS_VERTICAL_ONLY);

    Main(StringBuffer strbuf) {
        super("DateFormatSymbols Example");

        // Set the text and add the layout to text area.
        textArea.setText(strbuf.toString());
        add(textArea, BorderLayout.CENTER);

        // Set size and show window.
        Font f = new Font("Courier", Font.PLAIN, 12);
        textArea.setFont(f);
        textArea.setEditable(false);
        setSize(600, 480);
        show();
    }

    public static void main(String[] args) {
        StringBuffer strbuf = new StringBuffer(1500);

        // Create an array of all available locales.
        Locale[] locales = DateFormat.getAvailableLocales();

        // Add the default locale to the beginning so it's displayed first.
        Locale[] localesPlusDefault = new Locale[locales.length + 1];
        System.arraycopy(locales, 0, localesPlusDefault, 1, locales.length);
        localesPlusDefault[0] = Locale.getDefault();

        // Loop through all locales.
        for (int i = 0; i < localesPlusDefault.length; ++i) {

            // Build up string to display in a string buffer
            strbuf.append("--------------------------------------");
            strbuf.append("--------------------------------------\n");
            strbuf.append(localesPlusDefault[i].getDisplayName());
            strbuf.append("\n\n");

            // Create a new date format symbols for US
            DateFormatSymbols dfs =
                               new DateFormatSymbols(localesPlusDefault[i]);

            strbuf.append("AM-PM STRINGS:" + "\n");
            appendArray(dfs.getAmPmStrings(), strbuf);
            strbuf.append("ERAS:" + "\n");
            appendArray(dfs.getEras(), strbuf);
            strbuf.append("MONTHS:" + "\n");
            appendArray(dfs.getMonths(), strbuf);
            strbuf.append("SHORT MONTHS: " + "\n");
            appendArray(dfs.getShortMonths(), strbuf);
            strbuf.append("WEEKDAYS:" + "\n");
            appendArray(dfs.getWeekdays(), strbuf);
            strbuf.append("SHORT WEEKDAYS: " + "\n");
            appendArray(dfs.getShortWeekdays(), strbuf);
            strbuf.append("ZONE STRINGS:" + "\n");
            appendDeepArray(dfs.getZoneStrings(), strbuf);
            strbuf.append("LOCAL PATTERN CHARS: " + "\n");
            strbuf.append(dfs.getLocalPatternChars() + "\n\n");
        }
        new Main(strbuf);
    }

    // Append the contents of a 1-dimensional array to a string
    static void appendArray(Object[] a, StringBuffer strbuf) {
        for (int i=0; i<a.length; i++) {
            if (a[i].toString().length() == 0) {
                strbuf.append("(empty)" + "  ");
            } else {
                strbuf.append(a[i] + "  ");
            }
        }
        strbuf.append("\n");
    }

    // Print out the contents of a 2-dimensional array.
    static void appendDeepArray(Object[][] a, StringBuffer strbuf) {
        for (int i=0; i<a.length; i++) {
            for (int j=0; j<a[i].length; j++) {
                strbuf.append(a[i][j] + "   ");
            }
            strbuf.append("\n");
        }
    }
}
