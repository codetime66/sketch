import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = BigInteger.valueOf(5);
        BigInteger b = BigInteger.valueOf(3);

        System.out.println(a.mod(b));                       // 5%3 = 2
        System.out.println(a.negate().mod(b));              // -5%3 = 1
        //System.out.println(a.mod(b.negate()));            // ArithmeticException
        //System.out.println(a.mod(BigInteger.valueOf(0))); // ArithmeticException
//
    }
}