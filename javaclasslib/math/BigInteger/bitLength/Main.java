import java.math.*;

class Main {
    // Print the minimal representation of val
    static void printMinRep(long val) {
        BigInteger n  = BigInteger.valueOf(val);
        int blen = n.bitLength();
        System.out.print(n.signum() < 0 ? "1" : "0");
        for(int i=blen-1; i>=0; i--) {
            System.out.print(n.testBit(i) ? "1" : "0");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printMinRep(0);     // 0
        printMinRep(1);     // 01
        printMinRep(0xff);  // 011111111
        printMinRep(-1);    // 1
        printMinRep(-0xff); // 100000001
        printMinRep(-3);    // 101
        printMinRep(5);     // 0101
    }
}
