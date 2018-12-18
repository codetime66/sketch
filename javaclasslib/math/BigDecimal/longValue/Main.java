import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal("1.99");
        System.out.println(a.longValue());  // 1

        a = new BigDecimal("31323423423419083091823091283933");
        System.out.println(a.longValue());  // -5251313250005125155
//
    }
}
