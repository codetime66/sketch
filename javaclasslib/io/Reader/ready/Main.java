import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <file>");
            System.exit(-1);
        }
        try {
//          BufferedReader in = new BufferedReader(new FileReader(args[0]));
//          Reader in = new FileReader(args[0]);
            Reader in = new BufferedReader(new StringReader("This is a test"));
            char[] buf = new char[512];
            int howmany;
            while (true) {
                if (in.ready()) {
                    howmany = in.read(buf);
                    if (howmany <= 0) {
                        break;
                    } else {
                        System.out.println(new String(buf, 0, howmany));
                    }
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // Thread.currentThread().interrupt();
                        System.err.println(e);
                        break;
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
