import java.io.*;

class Main {
    public static void main(String[] args) {
        StringWriter out = new StringWriter();

        out.write("Java Class Libraries");
        for (char ch = 'a'; ch < 'z'; ch++ ) {
            out.write(ch);
            out.write(' ');
        }
        StringBuffer buf = out.getBuffer();
        buf.reverse();  // Make a change to the StringBuffer

        System.out.println(out.toString()); // writer is affected
// y x w v u t s r q p o n m l k j i h g f e d c b aseirarbiL ssalC avaJ

    }
}
