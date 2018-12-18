import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            StreamTokenizer tokens = new StreamTokenizer(
                new InputStreamReader(System.in));

            tokens.resetSyntax();
            tokens.wordChars('0', '9');        // make digit chars word chars
            tokens.wordChars('a', 'z');
            tokens.wordChars('A', 'Z');
            tokens.eolIsSignificant(true);
            tokens.quoteChar('"');             // " is quoted string delimiter
            tokens.whitespaceChars(0, ' ');
            tokens.whitespaceChars('$', '$');  // treat $ as white space
            tokens.lowerCaseMode(true);        // turn tokens to lowercase
            int c;
         out:
            while (true) {
                switch (c=tokens.nextToken()) {
                case StreamTokenizer.TT_EOF:
                    break out;
                case StreamTokenizer.TT_EOL:
                    System.out.print("\n" + tokens.lineno() + "\t");
                    break;
                case StreamTokenizer.TT_NUMBER:
                    // unexpected because we made digit chars words
                    System.err.println(
                        "warning: unexpected number: " + tokens.nval);
                    break;
                case StreamTokenizer.TT_WORD:
                    System.out.println("Identifier: " + tokens.sval);
                    break;
                case '"':
                    System.out.println("Quoted String: " + tokens.sval);
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
