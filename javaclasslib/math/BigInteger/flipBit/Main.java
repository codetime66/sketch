import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = BigInteger.valueOf(0);

        a = a.flipBit(100);
        System.out.println(a);        // 1267650600228229401496703205376

        a = a.flipBit(100);
        System.out.println(a);        // 0
//
    }
}