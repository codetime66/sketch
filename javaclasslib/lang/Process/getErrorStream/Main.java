import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            String cmd = "ls";
            Process child = Runtime.getRuntime().exec(cmd + " /notthere");
            // get error output from child process
            InputStream child_err = child.getErrorStream();
            int c;
            while ((c = child_err.read()) != -1) {
                System.out.print((char)c);
            }
            child_err.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
