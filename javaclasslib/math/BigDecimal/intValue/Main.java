import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal("1.99");

        System.out.println(a.intValue());  // 1

        a = new BigDecimal("23423419083091823091283933");
        System.out.println(a.intValue());  // -249268259
//
    }
}
