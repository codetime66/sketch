import java.util.*;

class Main {
    static void printDefaultTimeZone() {
        TimeZone tz = TimeZone.getDefault();

        System.out.println(tz.getID() + " " + tz.getRawOffset());
    }

    public static void main(String[] args) {
        // Print real default.
        printDefaultTimeZone();

        // Replace the default time zone (not a typical thing to do.)
        TimeZone tz = new SimpleTimeZone(1234567, "FOO");
        TimeZone.setDefault(tz);

        // Print new default.
        printDefaultTimeZone();
    }
}
