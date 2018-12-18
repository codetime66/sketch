import java.io.*;

class Main {
    public static void main(String[] args) {
        FileOutputStream stderr = 
            new FileOutputStream(FileDescriptor.err);
        FileOutputStream stdout = 
            new FileOutputStream(FileDescriptor.out);
        FileInputStream stdin =
            new FileInputStream(FileDescriptor.in);
        try {
            StringBuffer sb = new StringBuffer();
            int c;
            while ((c=stdin.read()) > -1) {
                if (c == '\n' || c == '\r')
                    break;
                sb.append((char)c);
            }
            // print to standard out
            byte[] buf = sb.toString().getBytes();
            stdout.write(buf);
            stdout.write('\n');
            stdout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
