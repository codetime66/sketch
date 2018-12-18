import java.math.*;

class Main {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        BigInteger prod = BigInteger.valueOf(1);

        for (int i=1; i<=n; i++) {
            prod = prod.multiply(BigInteger.valueOf(i));
        }
        System.out.println(prod);
    }
}
