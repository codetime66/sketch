import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            StreamTokenizer tokens = new StreamTokenizer(
                new InputStreamReader(System.in));

            tokens.resetSyntax();              // turn off everything else
            tokens.parseNumbers();
            tokens.whitespaceChars(0, ' ');    // must put spaces back

            int c;
         out:
            while (true) {
                c = tokens.nextToken();
                switch (tokens.ttype) {        // same as value of 'c'
                case StreamTokenizer.TT_EOF:
                    break out;
                case StreamTokenizer.TT_NUMBER:
                    System.out.println(tokens.toString());
                    break;
                case StreamTokenizer.TT_WORD:
                    System.err.println(
                        "warning: unexpected identifier: " + tokens.sval);
                    break;
                default:
                    System.out.println("Default: " + (char)c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
