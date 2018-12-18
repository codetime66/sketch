import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            String cmd = "ls";
            Process child = Runtime.getRuntime().exec(cmd);
            // get output from child process
            InputStream child_in = child.getInputStream();
            int c;
            while ((c = child_in.read()) != -1) {
                System.out.print((char)c);
            }
            child_in.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
