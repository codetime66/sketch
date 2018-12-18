import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input>");
            System.exit(-1);
        }
        try {
            InputStreamReader in1 = 
                new InputStreamReader(new FileInputStream(args[0]), "UTF8");
            InputStreamReader in2 = new InputStreamReader(System.in);

            System.out.println("in1 encoding: " + in1.getEncoding()); // UTF8
            System.out.println("in2 encoding: " + in2.getEncoding()); // 8859_1
            in1.close();
            in2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
