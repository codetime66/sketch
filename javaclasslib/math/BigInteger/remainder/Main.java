import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = BigInteger.valueOf(5);
        BigInteger b = BigInteger.valueOf(3);

        BigInteger ans = a.remainder(b);          // 5%3
        System.out.println(ans);                  // 2

        ans = a.remainder(b.negate());            // 5%-3
        System.out.println(ans);                  // 2

        ans = a.negate().remainder(b);            // -5%3
        System.out.println(ans);                  // -2

        ans = a.negate().remainder(b.negate());   // -5%-3
        System.out.println(ans);                  // -2

        //ans = a.remainder(BigInteger.valueOf(0)); // ArithmeticException
//
    }
}
