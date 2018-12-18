import java.io.*;

class Main {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.err.println("Usage: java Main <enc> <outputfile>");
        }
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1]), args[0]));
            
            out.write("This is a test");
            out.newLine();
            out.write('H');
            char[] buf = {'e', 'l', 'l', 'o'};
            out.write(buf);
            out.newLine();

            out.flush(); // for illustration only; close() will flush
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
