import java.util.*;

class test {
    public static void main(String[] args) {
        TimeZone tz = new SimpleTimeZone(TimeZone.getDefault().getRawOffset(), "JAV");

        for (int i=1; i<8; i++) {
            Calendar cal = new GregorianCalendar(1997, Calendar.APRIL, i-30);
            System.out.println(tz.getOffset(
                cal.get(Calendar.ERA),
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.DAY_OF_WEEK),
                0));
        }
    }
}