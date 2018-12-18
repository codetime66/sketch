import java.math.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
PrintStream out = System.out;
//
out.println(new BigDecimal("1.23").min(new BigDecimal("1.234")));  // 1.23
out.println(new BigDecimal("1.23").min(new BigDecimal("1.230")));  // 1.230
out.println(new BigDecimal("1.2300").min(new BigDecimal("1.23"))); // 1.23
//
    }
}
