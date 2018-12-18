import java.math.*;

class Main {
    // Returns x raised to the power of y.
    static void printPow(int x, int y) {
        System.out.println(BigInteger.valueOf(x).pow(y));
    }

    public static void main(String[] args) {
        printPow(0, 0);     // 1
        printPow(1, 0);     // 1
        printPow(-1, 0);    // 1
        printPow(1, 1);     // 1
        printPow(0, 1);     // 0
        //printPow(2, -1);  // ArithmeticException

        printPow(2, 3);     // 8
        printPow(-2, 3);    // -8

        printPow(12, 34);   // 4922235242952026704037113243122008064
    }
}