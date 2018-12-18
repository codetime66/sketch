import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal("1.99");
        System.out.println(a.floatValue());  // 1.99

        a = new BigDecimal(Double.MAX_VALUE);
        System.out.println(a.floatValue());  // Infinity

        a = new BigDecimal(-Double.MAX_VALUE);
        System.out.println(a.floatValue());  // -Infinity
//
    }
}
