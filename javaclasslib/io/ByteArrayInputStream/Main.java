import java.io.ByteArrayInputStream;

class Main {
    public static void main(String[] args) {
        byte[] inputbytes = { 'a', 'b', 'c', 'd', 'e'};
        ByteArrayInputStream in = new ByteArrayInputStream(inputbytes);

        System.out.println("Available: " + in.available());
        int b;

        while ((b=in.read()) >= 0)        // reads "abcde"
            System.out.print((char)b);
        in.reset();
        System.out.println();

        in.skip(3);                       // skip "abc"

        while ((b=in.read()) >= 0)        // reads "de"
            System.out.print((char)b);
        System.out.println();
    }
}
