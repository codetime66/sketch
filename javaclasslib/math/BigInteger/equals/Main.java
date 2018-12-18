import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = BigInteger.valueOf(1);
        BigInteger b = BigInteger.valueOf(2);

        System.out.println(b.equals(a.add(a)));    // true
        System.out.println(a.equals(null));        // false
//
    }
}