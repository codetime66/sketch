import java.math.*;
import java.util.Random;

class Main {
    // Returns gcd(x, y).
    static void printGCD(long x, long y) {
        System.out.println(BigInteger.valueOf(x).gcd(BigInteger.valueOf(y)));
    }

    public static void main(String[] args) {
        printGCD(0, 0);     // 0
        printGCD(1, 0);     // 1
        printGCD(0, 1);     // 1
        printGCD(1, 1);     // 1

        printGCD(5, 3);     // 1
        printGCD(25, 10);   // 5
        printGCD(-25, 10);  // 5

        Random rnd = new Random();
        while (true) {
            BigInteger a = new BigInteger(64, rnd);
            BigInteger b = new BigInteger(64, rnd);

            System.out.println("gcd("+a+","+b+")="+a.gcd(b));
        }
                // ... <sample output>
                // gcd(16952353143335189504,3481913592572825989)=1
                // gcd(4617725947040842835,16363096468092271150)=5
                // gcd(215684152070057280,10999101742231209159)=3
                // gcd(16492901384184808671,13519219882357115807)=1
                // gcd(18321175869446016956,16791079565960874729)=1
    }
}
