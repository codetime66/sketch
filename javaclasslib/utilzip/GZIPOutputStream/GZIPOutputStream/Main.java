import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        int len;
        byte[] buf = new byte[1024];

        try {
            GZIPOutputStream os = new GZIPOutputStream(System.out);

            while ((len = System.in.read(buf)) >= 0) {
                os.write(buf, 0, len);
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}