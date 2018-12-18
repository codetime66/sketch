import java.math.*;
class Main {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("10.00");

        print(a.divide(new BigDecimal("5"), BigDecimal.ROUND_UNNECESSARY)); 
                                                        // 2.00

        //print(a.divide(new BigDecimal("3"), BigDecimal.ROUND_UNNECESSARY)); 
                                                        // ArithmeticException

        print(a.divide(new BigDecimal("3"), BigDecimal.ROUND_DOWN)); 
                                                        // 3.33

        print(a.divide(new BigDecimal("3"), 10, BigDecimal.ROUND_DOWN));
                                                        // 3.3333333333

        print(a.divide(new BigDecimal("3"), 10, BigDecimal.ROUND_CEILING));
                                                        // 3.3333333334
    }

    static void print(BigDecimal a) {
        System.out.println(a);
    }
}
