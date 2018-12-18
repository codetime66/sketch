import java.util.*;
import java.text.*;

class Main extends Thread {
    static SimpleTimeZone tz;

    public static void main(String[] args) {
        // See example description.
        int BUG_WORKAROUND = 60*60*1000;

        // Create a new SimpleTimeZone object with the name JAV.
        // Use the local raw offset.
        tz = new SimpleTimeZone(TimeZone.getDefault().getRawOffset(), "JAV");

        Calendar calendar = Calendar.getInstance();

        // Determine the number of milliseconds so far today.
        int todayMillis = calendar.get(Calendar.HOUR)*60*60*1000
            + calendar.get(Calendar.MINUTE)*60*1000
            + calendar.get(Calendar.SECOND)*1000;

        // If PM, add another 12 hours.
        if (calendar.get(Calendar.AM_PM) == Calendar.PM) {
            todayMillis += 12*60*60*1000;
        }

        // Set the start of daylight savings time 10 seconds from now.
        tz.setStartRule(
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH),
            calendar.get(Calendar.DAY_OF_WEEK),
            todayMillis + 10000);  

        // Set the end of daylight savings time 20 seconds from now.
        tz.setEndRule(
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH),
            calendar.get(Calendar.DAY_OF_WEEK),
            todayMillis + 20000 + BUG_WORKAROUND);

        // Start a thread to monitor the time.
        new Main().start();
    }

    public void run() {
        DateFormat formatter = 
            new SimpleDateFormat("MMM dd HH:mm:ss zzz", Locale.US);
        formatter.setTimeZone(tz);

        while (true) {
            try {
                // Get the current time and date.
                Date date = new Date();

                // Print current time.
                System.out.print( (tz.inDaylightTime(date) ? 
                    "Daylight Savings Time - " : "Standard Time         - "));
                System.out.println(formatter.format(date));

                // Sleep for 3 seconds.
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
