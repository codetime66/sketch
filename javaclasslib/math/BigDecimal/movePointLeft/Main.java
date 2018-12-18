import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal("1.00");

        System.out.println(a.scale());     // 2

        a = a.movePointLeft(1);            
        System.out.println(a);             // 0.100
        System.out.println(a.scale());     // 3

        a = a.movePointLeft(-2);            
        System.out.println(a);             // 10.0
        System.out.println(a.scale());     // 1
//
    }
}
