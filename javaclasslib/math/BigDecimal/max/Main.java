import java.math.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
PrintStream out = System.out;
//
out.println(new BigDecimal("1.23").max(new BigDecimal("1.234")));  // 1.234
out.println(new BigDecimal("1.23").max(new BigDecimal("1.230")));  // 1.230
out.println(new BigDecimal("1.2300").max(new BigDecimal("1.23"))); // 1.23
//
    }
}
