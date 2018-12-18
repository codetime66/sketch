import java.math.*;
import java.util.Random;

class Main {
    // Prints x.modInverse(m).
    static void printModInverse(int x, int m) {
        BigInteger a = BigInteger.valueOf(x);
        System.out.println(a.modInverse(BigInteger.valueOf(m)));
    }

    public static void main(String[] args) {
        printModInverse(0, 1);       // 0
        printModInverse(1, 1);       // 0
        printModInverse(5, 3);       // 2
        printModInverse(10, 3);      // 1
        printModInverse(9, 7);       // 4
        //printModInverse(10, 5);    // ArithmeticException
        //printModInverse(5, 0);     // ArithmeticException

        Random rnd = new Random();
        BigInteger ONE = BigInteger.valueOf(1);
        while (true) {
            BigInteger a = new BigInteger(32, rnd);
            BigInteger m = new BigInteger(32, rnd);
            
            if (a.gcd(m).intValue() == 1) {
                BigInteger inv = a.modInverse(m);
                System.out.println(
                    a + " * " + inv + " (mod " + m + ") = " + 
                        a.multiply(inv).mod(m));
            }
        }
                // 1571004015 * 385084739 (mod 1370659187) = 1
                // 603331458 * 255317344 (mod 1317188219) = 1
                // 1424330431 * 72896381 (mod 206427394) = 1
                // 1160587373 * 532755746 (mod 1370179997) = 1
                // 488707349 * 2846222921 (mod 4190656154) = 1
                // 4290966619 * 1930549735 (mod 3246793271) = 1
                // 487989081 * 1489938937 (mod 3169209884) = 1
                // 3717708439 * 252962773 (mod 533163014) = 1
                // 3988371331 * 1266113047 (mod 1511544858) = 1
    }
}
