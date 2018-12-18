import java.util.zip.*;

class Main {
    public static void main(String[] args) {
        Inflater inf = new Inflater();
        byte[] buf = new byte[]{'0', '1', '2'};

        inf.setInput(buf);
        try {
            inf.inflate(buf);
        } catch (DataFormatException e) {
            System.err.println("The compressed data is invalid");
        }
    }
}