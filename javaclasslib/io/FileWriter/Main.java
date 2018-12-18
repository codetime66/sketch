import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: java Main <outputfile>");
            System.exit(-1);
        }

        try {
            FileWriter out = new FileWriter(args[0]);
            out.write("Java Class Libraries\n");
            out.flush();
            out.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
