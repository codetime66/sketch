import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = BigInteger.valueOf(-Long.MAX_VALUE);
        System.out.println(a.doubleValue());        // -9.223372036854776E18

        // Make a number with a very large magnitude.
        a = a.pow(99);
        System.out.println(a.doubleValue());        // -Infinity

        // Make a number with a very large magnitude.
        a = a.pow(2);
        System.out.println(a.doubleValue());        // Infinity
//
    }
}
