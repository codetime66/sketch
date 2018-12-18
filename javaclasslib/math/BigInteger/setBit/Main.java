import java.math.*;

class Main {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("101010", 2);   // 101010
        System.out.println(a.setBit(2).toString(2));  // 101110

        a = new BigInteger("-111", 2);                //          ...11111001
        System.out.println(a.setBit(2).toString(2));  //    -11   ...11111101
    }
}