import java.util.*;

class Main {
    public static void main(String[] args) {
        TimeZone tz = TimeZone.getTimeZone("PST");

	System.out.println(tz.getOffset(GregorianCalendar.AD,
	    1997, Calendar.APRIL, 6, Calendar.SUNDAY, 2*60*60*1000-1));
	// -28800000

	System.out.println(tz.getOffset(GregorianCalendar.AD,
            1997, Calendar.APRIL, 6, Calendar.SUNDAY, 2*60*60*1000));
	// -25200000

	System.out.println(tz.getOffset(GregorianCalendar.AD,
            1997, Calendar.OCTOBER, 26, Calendar.SUNDAY, 1*60*60*1000-1));
	// -25200000

	System.out.println(tz.getOffset(GregorianCalendar.AD,
            1997, Calendar.OCTOBER, 26, Calendar.SUNDAY, 1*60*60*1000));
	// -28800000
    }
}
