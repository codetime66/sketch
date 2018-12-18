import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = BigInteger.valueOf(10);
        BigInteger b = BigInteger.valueOf(3);

        System.out.println(a.divide(b));            // 3
        System.out.println(a.divide(b.negate()));   // -3
        System.out.println(a.negate().divide(b));   // -3
//
    }
}
