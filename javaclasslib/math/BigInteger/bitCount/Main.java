import java.math.*;

class Main {
    public static void main(String[] args) {
//
        System.out.println(new BigInteger("0", 2).bitCount());     // 0
        System.out.println(new BigInteger("1", 2).bitCount());     // 1
        System.out.println(new BigInteger("10", 2).bitCount());    // 1
        System.out.println(new BigInteger("1010", 2).bitCount());  // 2
        System.out.println(new BigInteger("-1", 2).bitCount());    // 0
        System.out.println(new BigInteger("-10", 2).bitCount());   // 1
        System.out.println(new BigInteger("-1010", 2).bitCount()); // 2
//
    }
}