import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = BigInteger.valueOf(123);
        System.out.println(a.shiftRight(1));   // 61
        System.out.println(a.shiftRight(100)); // 0

        a = BigInteger.valueOf(-123);
        System.out.println(a.shiftRight(1));   // -62
        System.out.println(a.shiftRight(100)); // -1

        a = BigInteger.valueOf(123);
        System.out.println(a.shiftLeft(1));    // 246
        System.out.println(a.shiftLeft(100));  // 155921023828072216384094494261248

        a = BigInteger.valueOf(-123);
        System.out.println(a.shiftLeft(1));    // -246
        System.out.println(a.shiftLeft(100));  // -155921023828072216384094494261248

        // Shift with negative values.
        a = BigInteger.valueOf(123);
        System.out.println(a.shiftRight(-1));  // 246
        System.out.println(a.shiftLeft(-1));   // 61
//
    }
}