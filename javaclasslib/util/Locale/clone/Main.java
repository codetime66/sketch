import java.util.*;

class Main {
    public static void main(String[] args) {
        Locale defloc = Locale.getDefault();
        Locale loc = (Locale)defloc.clone();

        System.out.println((loc == defloc));             // false
        System.out.println((loc.equals(defloc)));        // true
    }
}
