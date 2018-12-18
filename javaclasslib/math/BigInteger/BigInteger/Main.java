import java.math.*;
import java.util.Random;

class Main {
    public static void main(String args[]) {
//
    // BigInteger(byte[] bval)
        BigInteger a = new BigInteger(new byte[]{0x1, 0});     
        System.out.println(a);                             // 256

        a = new BigInteger(new byte[]{(byte)128});    
        System.out.println(a);                             // -128

    // BigInteger(int signum, byte[] magnitude)
        a = new BigInteger(1, new byte[]{(byte)128});    
        System.out.println(a);                             // 128

        a = new BigInteger(0, new byte[]{(byte)0});        
        System.out.println(a);                             // 0
        a = new BigInteger(0, new byte[]{});              
        System.out.println(a);                             // 0
        //a = new BigInteger(0, new byte[]{(byte)128});    // NumberFormatEx

        a = new BigInteger(-1, new byte[]{(byte)128});    
        System.out.println(a);                             // -128

    // BigInteger(int numBits, Random rnd)
        a = new BigInteger(0, new Random());
        System.out.println(a);                             // 0

        a = new BigInteger(1, new Random());
        System.out.println(a);                             // 1

        // a = new BigInteger(-1, new Random());           // IllegalArgumentEx

        a = new BigInteger(32, new Random());
        System.out.println(a);                             // 17950393

    // BigInteger(int numBits, int certainty, Random rnd)
        a = new BigInteger(32, 0, new Random());
        System.out.println(a);                             // 3681762179

        a = new BigInteger(32, 1000, new Random());
        System.out.println(a);                             // 2473873543

        a = new BigInteger(32, -10, new Random());
        System.out.println(a);                             // 3821485007
//
    }
}
