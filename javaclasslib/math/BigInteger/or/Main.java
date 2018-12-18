import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = new BigInteger("110111", 2);    // 110111
        BigInteger b = new BigInteger("101010", 2);    // 101010
        System.out.println(a.or(b).toString(2));       // 111111

        a = new BigInteger("-0111", 2);                // 111001
        b = new BigInteger("101010", 2);               // 101010
        System.out.println(a.or(b).toString(2));       // -101
//
    }
}
