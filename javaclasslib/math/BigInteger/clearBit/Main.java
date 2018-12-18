import java.math.*;

class Main {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("101010", 2);    // 101010
        System.out.println(a.clearBit(1).toString(2)); // 101000

        a = new BigInteger("-0111", 2);                //          ...11111001
        System.out.println(a.clearBit(5).toString(2)); // -100111  ...11011001
    }
}