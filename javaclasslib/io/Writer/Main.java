import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <output>");
            System.exit(-1);
        }
        String str = "\u597d\u5929";  // beautiful day

        try {
            Writer out = 
                new OutputStreamWriter(new FileOutputStream(args[0]), "UTF8");

            out.write(str);
            out.flush();  // not necessary because close() already does it
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
