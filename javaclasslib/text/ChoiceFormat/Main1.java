import java.text.*;
import java.util.*;

public class Main1 {
    public static void main (String args[])
    {
        ChoiceFormat fmt = new ChoiceFormat(
            "-1#is negative|" +
            "0#is zero up to less than 1 |" +
            "1#is exactly 1 |" +
            "1.0<is greater than 1 up to 2 |" +
            "2#is exactly 2 |" +
            "2<is greater than 2");

        System.out.println("Pattern : " + fmt.toPattern() + "\n");

        print(Double.NaN, fmt);                // "is negative"
        print(Double.NEGATIVE_INFINITY, fmt);  // "is negative"
        print(-1.0, fmt);                      // "is negative"
        print(-0.5, fmt);                      // "is negative"
        print(0, fmt);                         // "is zero up to less than 1"
        print(0.9, fmt);                       // "is zero up to less than 1"
        print(1.0, fmt);                       // "is exactly 1"
        print(1.5, fmt);                       // "is greater than 1 up to 2"
        print(2, fmt);                         // "is exactly 2"
        print(2.1, fmt);                       // "is greater than 2"
        print(Double.POSITIVE_INFINITY, fmt);  // "is greater than 2"
    }

    static void print(double value, ChoiceFormat cf) {
        String str = "format(" + value + "):";
        System.out.print(str);
        for (int i = 0; i < 22 - str.length(); i++) {
            System.out.print(" ");
        }
        System.out.println(cf.format(value));
    }
}
