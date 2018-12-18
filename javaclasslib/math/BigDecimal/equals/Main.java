import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal("-123.45");
        BigDecimal b = new BigDecimal("123.45");
        System.out.println(a.negate().equals(b));    // true

        a = new BigDecimal("1.23");
        b = new BigDecimal("1.230");
        System.out.println(a.equals(b));             // false

        System.out.println(a.compareTo(b));          // 0
//
    }
}
