import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal(1.23);
        System.out.println(a);
            // 1.229999999999999982236431605997495353221893310546875

        double d = Double.MAX_VALUE * Double.MAX_VALUE;
        // a = new BigDecimal(d);                      // NumberFormatException

        a = new BigDecimal("-123.456");
        System.out.println(new BigDecimal("123."));    // 123
        System.out.println(new BigDecimal(".123"));    // 0.123
        System.out.println(new BigDecimal(".0"));      // 0.0
        System.out.println(new BigDecimal("0."));      // 0
        // a = new BigDecimal(" 123.456");             // NumberFormatException
        // a = new BigDecimal("123.456 ");             // NumberFormatException
        // a = new BigDecimal("+123.456");             // NumberFormatException
        // a = new BigDecimal(".");                    // NumberFormatException

        a = new BigDecimal(new BigInteger("123"), 2);
        System.out.println(a);                         // 1.23
//
    }
}
