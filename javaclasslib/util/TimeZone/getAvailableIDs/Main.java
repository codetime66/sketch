import java.util.*;
import java.math.*;

class Main {
    // Converts a time offset (in milliseconds) to hours.
    static String convertToHours(int time) {
        BigDecimal n = BigDecimal.valueOf(time);
        n = n.setScale(10);
        n = n.divide(BigDecimal.valueOf(60*60*1000), BigDecimal.ROUND_DOWN);

        // Get rid of trailing zeroes.
        int scale = n.scale();
        while (scale >= 0) {
            try {
                n = n.setScale(--scale, BigDecimal.ROUND_UNNECESSARY);
            } catch (ArithmeticException e) {
                break;
            }
        }

        // Pad the result out to 7 spaces.
        //String result = n.toString();
        String result = "" + n;
        int len = 7-result.length();
        for (int i=0; i<len; i++) {
            result += " ";
        }
        return result;
    }

    public static void main(String[] args) {
        String[] ids = TimeZone.getAvailableIDs();

        // Print header.
        System.out.println("ID  Raw Offset    Offset   Daylight Savings");

        // Print info.
        for (int i=0; i<ids.length; i++) {
            Calendar cal = Calendar.getInstance();
            SimpleTimeZone tz = (SimpleTimeZone)TimeZone.getTimeZone(ids[i]);

            System.out.print(ids[i] + "     " 
                + convertToHours(tz.getRawOffset()) + "    "
                + convertToHours(tz.getOffset(
                    cal.get(Calendar.ERA),
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH),
                    cal.get(Calendar.DAY_OF_WEEK),
                    cal.get(Calendar.MILLISECOND))) + "      ");

            if (tz.useDaylightTime()) {
                System.out.print(tz.inDaylightTime(cal.getTime()));
            }
            System.out.println();
        }
    }
}