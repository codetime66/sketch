import java.math.*;

class Main {
    public static void main(String[] args) {
//
        BigInteger a = new BigInteger("110111", 2);  //               110111
        BigInteger b = new BigInteger("101010", 2);  //               101010
        System.out.println(a.xor(b).toString(2));    //   11101       011101

        a = new BigInteger(  "-111", 2);             //          ...11111001
        b = new BigInteger("101010", 2);             //             00101010
        System.out.println(a.xor(b).toString(2));    // -101101  ...11010011

        a = new BigInteger(   "-111", 2);            //          ...11111001
        b = new BigInteger("-101010", 2);            //          ...11010110
        System.out.println(a.xor(b).toString(2));    //  101111     00101111
//
    }
}