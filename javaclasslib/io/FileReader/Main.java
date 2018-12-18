import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: java Main <inputfile>");
        }
        try {
            FileReader in = new FileReader(args[0]);
            Writer out = new PrintWriter(System.out);
            char[] buf = new char[512];
            int howmany;
            while ((howmany = in.read(buf)) >= 0) {
                out.write(buf, 0, howmany);
            }
            out.flush();
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
