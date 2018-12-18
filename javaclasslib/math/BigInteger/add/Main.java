import java.math.*;

class Main {
    public static void main(String[] args) {
        BigInteger sum = BigInteger.valueOf(0);

        for (int i=0; i<args.length; i++) {
            sum = sum.add(new BigInteger(args[i]));
        }
        System.out.println(sum);
    }
}
