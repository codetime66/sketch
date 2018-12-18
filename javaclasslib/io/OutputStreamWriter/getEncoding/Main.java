import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <outputfile>");
            System.exit(-1);
        }
        try {
            OutputStreamWriter o1 = 
                new OutputStreamWriter(new FileOutputStream(args[0]), "UTF8");
            OutputStreamWriter o2 = new OutputStreamWriter(System.out);

            System.out.println("o1 encoding: " + o1.getEncoding()); // UTF8
            System.out.println("o2 encoding: " + o2.getEncoding()); // 8859_1
            o1.close();
            o2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
