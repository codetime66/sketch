import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = BigDecimal.valueOf(0);
        BigDecimal b = BigDecimal.valueOf(1);

        System.out.println(a.subtract(b));             // -1

        System.out.println(BigDecimal.valueOf(1, 3));  // 0.001
//
    }
}
