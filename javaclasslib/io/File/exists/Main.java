import java.io.File;
class Main {
    public static void main(String[] args) {
        for (char ch='C'; ch<='Z'; ch++) {
            File f = new File(ch + ":");
            if (f.exists()) {
                System.out.println(f.toString());
            }
        }
    }
}
