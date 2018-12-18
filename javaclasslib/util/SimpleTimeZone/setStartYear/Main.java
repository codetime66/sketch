import java.util.*;

class Main {
    public static void main(String[] args) {
        // Daylight savings for PST is between April and October.
        SimpleTimeZone tz = (SimpleTimeZone)TimeZone.getTimeZone("PST");

        // Create an arbitrary point in time.
        Calendar cal = new GregorianCalendar(1000, Calendar.JULY, 4);
    
        // In daylight savings.
        System.out.println(tz.inDaylightTime(cal.getTime()));    // true
        
        // Set an abitrary year in which daylight saving time takes effect.
        tz.setStartYear(1500);

        // Not in daylight savings.
        System.out.println(tz.inDaylightTime(cal.getTime()));    // false
    }
}