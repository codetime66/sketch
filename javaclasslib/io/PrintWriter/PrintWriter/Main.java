import java.io.PrintWriter;

class Main {
    public static void main(String[] args) {
        // Create a writer for a byte output stream
        PrintWriter out = new PrintWriter(System.out);
        out.println("Hello There!");
        out.close();
    }
}
