import java.math.*;
import java.util.Random;

class Main {
    // Print the low-order 32-bits of n.
    static void print(BigInteger n) {
        for(int i=31; i>=0; i--) {
            System.out.print(n.testBit(i) ? "1" : "0");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print(BigInteger.valueOf(0));       // 00000000000000000000000000000000
        print(BigInteger.valueOf(1));       // 00000000000000000000000000000001
        print(BigInteger.valueOf(-1));      // 11111111111111111111111111111111
        print(BigInteger.valueOf(65536));   // 00000000000000010000000000000000
        print(BigInteger.valueOf(-65536));  // 11111111111111110000000000000000

        print(new BigInteger(32, new Random())); 
                                            // 10111010100011101001001l01110010
    }
}
