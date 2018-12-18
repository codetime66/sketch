import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigDecimal a = new BigDecimal("1.0");

        System.out.println(a.scale());     // scale 1

        a = a.movePointRight(2);            
        System.out.println(a);             // 100
        System.out.println(a.scale());     // scale 0

        a = a.movePointRight(-3);            
        System.out.println(a);             // .100
        System.out.println(a.scale());     // scale 3
//
    }
}
