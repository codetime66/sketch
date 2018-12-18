import java.math.*;

class Main {
    // Creates a BigInteger object, calls toByteArray() and prints the result.
    static void print(int n) {
        byte[] buf = BigInteger.valueOf(n).toByteArray();

        System.out.print("new byte[]{");
        for (int i=0; i<buf.length; i++) {
            if (i>0) {
                System.out.print(" ,");
            }
            System.out.print("0x"+Integer.toHexString(buf[i]&0xff));
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        print(0);      // new byte[]{0x0}
        print(1);      // new byte[]{0x1}
        print(-1);     // new byte[]{0xff}
        print(0x80);   // new byte[]{0x0 ,0x80}
        print(-0x80);  // new byte[]{0x80}
    }
}
