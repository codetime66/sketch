import java.math.*;

class Main {
    static void round(String num, int scale, int roundingMode) {
        System.out.println(new BigDecimal(num).setScale(scale, roundingMode));
    }

    public static void main(String[] args) {
        // Increasing the scale.
        BigDecimal a = new BigDecimal("1.23");
        System.out.println(a.setScale(3));               // 1.230

        // Decreasing the scale.
        round(".5", 0, BigDecimal.ROUND_CEILING);        // 1
        round(".5", 0, BigDecimal.ROUND_DOWN);           // 0
        round(".5", 0, BigDecimal.ROUND_FLOOR);          // 0
        round(".5", 0, BigDecimal.ROUND_HALF_DOWN);      // 0
        round(".5", 0, BigDecimal.ROUND_HALF_EVEN);      // 0
        round(".5", 0, BigDecimal.ROUND_HALF_UP);        // 1
        round(".4", 0, BigDecimal.ROUND_HALF_UP);        // 0
        round(".4", 0, BigDecimal.ROUND_UP);             // 1
        //round(".5", 0, BigDecimal.ROUND_UNNECESSARY);  // ArithmeticException
        round("1.0", 0, BigDecimal.ROUND_UNNECESSARY);   // 1

        round("-.5", 0, BigDecimal.ROUND_CEILING);       // 0
        round("-.5", 0, BigDecimal.ROUND_DOWN);          // 0
        round("-.5", 0, BigDecimal.ROUND_FLOOR);         // -1
        round("-.5", 0, BigDecimal.ROUND_HALF_DOWN);     // 0
        round("-.5", 0, BigDecimal.ROUND_HALF_EVEN);     // 0
        round("-.5", 0, BigDecimal.ROUND_HALF_UP);       // -1
        round("-.4", 0, BigDecimal.ROUND_HALF_UP);       // 0
        round("-.4", 0, BigDecimal.ROUND_UP);            // -1
        //round("-.5", 0, BigDecimal.ROUND_UNNECESSARY); // ArithmeticException
        round("-1.0", 0, BigDecimal.ROUND_UNNECESSARY);  // -1
    }
}
