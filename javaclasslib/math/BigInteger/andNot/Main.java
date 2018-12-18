import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = new BigInteger("110111", 2);   //               110111
        BigInteger b = new BigInteger(  "1010", 2);   //    (~b)  ...11110101
        System.out.println(a.andNot(b).toString(2));  //  110101       110101

        a = new BigInteger("-0111", 2);               //          ...11111001
        b = new BigInteger( "1010", 2);               //    (~b)  ...11110101
        System.out.println(a.andNot(b).toString(2));  //   -1111  ...11110001
//
    }
}