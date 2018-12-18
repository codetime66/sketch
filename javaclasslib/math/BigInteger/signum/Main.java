import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = BigInteger.valueOf(-1);
        System.out.println(a.signum());     // -1

        a = BigInteger.valueOf(0);
        System.out.println(a.signum());     // 0

        a = BigInteger.valueOf(123);
        System.out.println(a.signum());     // 1
//
    }
}
