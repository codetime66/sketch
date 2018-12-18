import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal("1.23");
        BigDecimal b = new BigDecimal("1.234");
        System.out.println(a.compareTo(b));         // -1
        System.out.println(b.compareTo(a));         // 1

        a = new BigDecimal("1.23");
        b = new BigDecimal("1.2300");
        System.out.println(a.compareTo(b));         // 0
        System.out.println(a.equals(b));            // false
//
    }
}
