import java.util.*;
import java.text.*;

class Main {
    static String getDate(Date date, TimeZone tz) {
        DateFormat formatter
            = new SimpleDateFormat("MMM dd HH:mm:ss zzz", Locale.US);
        formatter.setTimeZone(tz);
        return formatter.format(date);
    }

    public static void main(String[] args) {
        TimeZone tz = (TimeZone)TimeZone.getDefault().clone();

        System.out.println(getDate(new Date(), tz));

        // Change the time zone ID.
        tz.setID("JAV");

        // Advance the offset by 30 minutes.
        tz.setRawOffset(tz.getRawOffset() + 1000*60*60/2);

        System.out.println(getDate(new Date(), tz));
    }
}