import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            BufferedWriter out = 
                new BufferedWriter(new OutputStreamWriter(System.out));

            out.write("This is a test.");
            out.newLine();
            out.flush();   // not needed; for example only; close will flush
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
