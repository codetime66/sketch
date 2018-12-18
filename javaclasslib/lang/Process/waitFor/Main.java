import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            String cmd = "ls";
            Process child = Runtime.getRuntime().exec(cmd);
            // Wait for child to finish
            int status;
            try {
                status = child.waitFor();
            } catch (InterruptedException e) {
                status = -1;
            }
            // Display exit status of subprocess
            if (status == 0)
                System.out.println("process successfully completed.");
            else
                System.out.println("process exited with " + status);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
