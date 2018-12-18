import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal(1.23);
        System.out.println(a.doubleValue());        // 1.23

        // Make a very large number.
        a = new BigDecimal(Double.MAX_VALUE);
        a = a.add(a);
        System.out.println(a.doubleValue());        // Infinity
//
    }
}
