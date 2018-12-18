import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal("1.23");
        System.out.println(a.abs());            // 1.23

        a = new BigDecimal(-6.25);
        System.out.println(a);			// -6.25

        System.out.println(a.abs());            // 6.25
//
    }
}
