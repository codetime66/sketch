import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal(-1.23);
        System.out.println(a.signum());     // -1

        a = new BigDecimal(0);
        System.out.println(a.signum());     // 0

        a = new BigDecimal(1.23);
        System.out.println(a.signum());     // 1
//
    }
}
