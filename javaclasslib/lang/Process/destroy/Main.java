import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            String cmd = "ls";
            // read first 5 lines from 'ls' and then destroy process
            Process child = Runtime.getRuntime().exec(cmd);
            InputStream in = child.getInputStream();
            int c, newline = 0;
            // read first 5 lines and then stop
            while ((c = in.read()) != -1 && newline < 5) {
                char ch = (char)c;
                System.out.print(ch);
                if (ch == '\n')
                    ++newline;
            }
            in.close();
            child.destroy(); // destroy process
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
