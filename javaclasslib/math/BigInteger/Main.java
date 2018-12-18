import java.math.*;

class Main {
    public static void main(String[] args) {
        BigInteger f1 = BigInteger.valueOf(1);
        BigInteger f2 = f1;

        // Print first two Fibonacci nubmers.
        System.out.println(1);
        System.out.println(1);

        // Print the remaining Fibonacii numbers.
        while (true) {
            BigInteger sum = f1.add(f2);
            f1 = f2;
            f2 = sum;
            System.out.println(sum);
        }
    }
}
