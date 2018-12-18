import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            String str = "This is a test";
            byte[] buf = str.getBytes("NonExistentEnc");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
