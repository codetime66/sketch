import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger[] ans;
        BigInteger a = BigInteger.valueOf(5);
        BigInteger b = BigInteger.valueOf(3);

        ans = a.divideAndRemainder(b);                    // 5/3
        System.out.println(ans[0]+" "+ans[1]);            // 1 2

        ans = a.divideAndRemainder(b.negate());           // 5/-3
        System.out.println(ans[0]+" "+ans[1]);            // -1 2

        ans = a.negate().divideAndRemainder(b);           // -5/3
        System.out.println(ans[0]+" "+ans[1]);            // -1 -2

        ans = a.negate().divideAndRemainder(b.negate());  // -5/-3
        System.out.println(ans[0]+" "+ans[1]);            // 1 -2

        //ans = a.divideAndRemainder(BigInteger.valueOf(0)); // ArithmeticException

//
    }
}
