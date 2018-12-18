import java.io.*;

class Main {
    public static void main(String[] args) {
        // some code that captures output from 'ls'
        try {
            String cmd = "ls";
            Process child = Runtime.getRuntime().exec(cmd);

            // Wait for child to finish
            try {
                child.waitFor();
            } catch (InterruptedException e) {
            }
            // Display exit status of subprocess
            int status = child.exitValue();
            if (status == 0)
                System.out.println("process successfully completed.");
            else
                System.out.println("process exited with " + status);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
