import java.math.*;

class Main {
    public static void main(String[] args) {
        int start = Integer.parseInt(args[0]);
        int len = start + Integer.parseInt(args[1]);
        for (int i=start; i < len; i++) {
            // Do the check.
            if (BigInteger.valueOf(i).isProbablePrime(20)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
