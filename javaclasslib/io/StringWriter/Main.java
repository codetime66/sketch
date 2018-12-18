import java.io.*;

class Main {
    public static void main(String[] args) {
        StringWriter out = new StringWriter();

        out.write("Java Class Libraries");
        for (char ch = 'a'; ch < 'z'; ch++ ) {
            out.write(ch);
            out.write(' ');
        }
        System.out.println(out.toString());
// Java Class Librariesa b c d e f g h i j k l m n o p q r s t u v w x y z
    }
}
