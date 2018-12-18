import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = BigInteger.valueOf(0);        //
        System.out.println(a.not().toString(2));     //      -1   ...111111111

        a = new BigInteger("101010", 2);             //                 101010
        System.out.println(a.not().toString(2));     // -101011   ...111010101

        a = new BigInteger("-101", 2);               //           ...111111011
        System.out.println(a.not().toString(2));     //     100            100
//
    }
}
