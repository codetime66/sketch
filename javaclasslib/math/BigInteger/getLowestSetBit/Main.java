import java.math.*;

class Main {
    public static void main(String[] args) {
//
        System.out.println(new BigInteger("0", 2).getLowestSetBit());     // -1
        System.out.println(new BigInteger("1", 2).getLowestSetBit());     // 0
        System.out.println(new BigInteger("1000", 2).getLowestSetBit());  // 3
        System.out.println(new BigInteger("-1", 2).getLowestSetBit());    // 0
        System.out.println(new BigInteger("-1000", 2).getLowestSetBit()); // 3
        System.out.println(new BigInteger("11", 2).getLowestSetBit());    // 0
        System.out.println(new BigInteger("-11", 2).getLowestSetBit());   // 0
//
    }
}
