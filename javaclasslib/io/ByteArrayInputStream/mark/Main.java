import java.io.ByteArrayInputStream;

class Main {
    public static void main(String[] args) {
        byte[] inputbytes = { 'a', 'b', 'c', 'd', 'e'};
        ByteArrayInputStream in = new ByteArrayInputStream(inputbytes);

        System.out.println("Available: " + in.available());
        int b;

        for (int i = 0; i < 3; i++) {
            b=in.read();                  // reads "abc"
            System.out.print((char)b);
        }
        System.out.println();
        in.mark(0);                       // mark position
        while ((b=in.read()) >= 0)        // reads "de"
            System.out.print((char)b);
        System.out.println();

        in.reset();                       // go back to mark
        while ((b=in.read()) >= 0)        // reads "de"
            System.out.print((char)b);
        System.out.println();
    }
}
