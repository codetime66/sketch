import java.math.*;

class Main {
    public static void main(String[] args) {
        for (int i=0; i<args.length; i++) {
            System.out.println("sqrt("+args[i]+") = " +
                sqrt(BigDecimal.valueOf(Integer.parseInt(args[i]))));
        }
    }

    public static BigDecimal sqrt(BigDecimal n) {
        BigDecimal TWO = BigDecimal.valueOf(2);

        // First approximation.
        BigDecimal x = n.divide(BigDecimal.valueOf(3), 20, 
            BigDecimal.ROUND_DOWN);
        BigDecimal lastX = BigDecimal.valueOf(0);

        for (int i=0; i<50; i++) {
            x = n.add(x.multiply(x)).divide(x.multiply(TWO), 20, 
                BigDecimal.ROUND_DOWN);
            if (x.compareTo(lastX) == 0) {
                break;
            }
            lastX = x;
        }
        return x;
    }
}
