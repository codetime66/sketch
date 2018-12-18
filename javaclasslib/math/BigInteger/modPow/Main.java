import java.math.*;

class Main {
    // Prints x.modPow(y, m).
    static void printModPow(int x, int y, int m) {
        BigInteger a = BigInteger.valueOf(x);
        BigInteger b = BigInteger.valueOf(y);
        BigInteger mod = BigInteger.valueOf(m);

        System.out.println(a.modPow(b, mod));
    }
    public static void main(String[] args) {
        printModPow(0, 0, 7);    // 1        0^0 (mod 7) == 1
        printModPow(11, 1, 7);   // 4        11^1 (mod 7) == 4
        printModPow(11, 2, 7);   // 2        11^2 (mod 7) == 2
        printModPow(11, 3, 7);   // 1        11^3 (mod 7) == 1
        printModPow(11, 4, 7);   // 4        11^4 (mod 7) == 4
        printModPow(11, 5, 7);   // 2        11^5 (mod 7) == 2


    // mod() behavior
        printModPow(0, 1, 7);    // 0         0 (mod 3) 
        printModPow(-1, 1, 7);   // 6        -1 (mod 7) == 6
        printModPow(10, 1, 7);   // 3        10 (mod 7) == 3

    // modInverse() behavior
        //printModPow(0, -1, 7); // ArithmeticException: not invertible.
        printModPow(11, -1, 7);  // 2        2*11 (mod 7) == 1
        printModPow(-1, -1, 7);  // 6        6*-1 (mod 7) == 1
    }
}
