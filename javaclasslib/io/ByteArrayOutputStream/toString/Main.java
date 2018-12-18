import java.io.*;

class Main {
    public static void main(String[] args) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write('a');
        out.write('b');
        out.write('c');

        System.out.println(out.toString());   // abc
    }
}
