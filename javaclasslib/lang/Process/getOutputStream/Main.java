import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            String cmd = "cat";
            Process child = Runtime.getRuntime().exec(cmd);
            // stream for feeding input to child process
            PrintWriter out = new PrintWriter(child.getOutputStream());
            out.println("hello world!");
            out.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
