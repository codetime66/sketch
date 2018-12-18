import java.math.*;
class test {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("10");
        a = a.divide(new BigDecimal("3"), 20, BigDecimal.ROUND_DOWN);
        System.out.println(a);
    }
}
