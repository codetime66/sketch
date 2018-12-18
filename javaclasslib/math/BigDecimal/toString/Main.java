import java.math.*;

class Main {
    public static void main(String[] args) {
//
        // The scale is preserved.
        BigDecimal a = new BigDecimal("-123.000");
        System.out.println(a.toString());    // "-123.000"

        a = new BigDecimal(new BigInteger("123", 10), 5);
        String s = a.toString();
        System.out.println(s);               // "0.00123"

        // Use the string to create an identical big decimal value.
        a = new BigDecimal(s);
        System.out.println(a);               // "0.00123"
//
    }
}
