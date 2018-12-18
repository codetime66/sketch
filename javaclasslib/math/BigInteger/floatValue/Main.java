import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = BigInteger.valueOf(-Long.MAX_VALUE);
        System.out.println(a.floatValue());        // -9.223372E18

        // Make a number with a very large magnitude.
        a = a.pow(99);
        System.out.println(a.floatValue());        // -Infinity

        // Make a number with a very large magnitude.
        a = a.pow(2);
        System.out.println(a.floatValue());        // Infinity
//
    }
}
