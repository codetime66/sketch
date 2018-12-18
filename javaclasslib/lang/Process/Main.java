import java.io.*;

class Main {
    public static void main(String[] args) {
        // some code that captures output from 'ls'
        try {
            String cmd = "ls";
            Process child = Runtime.getRuntime().exec(cmd);
            InputStream in = child.getInputStream();
            int c;
            // echo output of 'ls'
            while ((c = in.read()) != -1) {
                System.out.print((char)c);
            }
            in.close();
            // Wait for subprocess to exit
            try {
                child.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Display exit status of subprocess
            System.out.println("child exited with " + child.exitValue());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
