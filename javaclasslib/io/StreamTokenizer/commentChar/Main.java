import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            StreamTokenizer tokens = new StreamTokenizer(
                new InputStreamReader(System.in));
            // turn off single slash as comment char, this is required
            // for slashStar comments to work properly
            tokens.ordinaryChar('/');        
            tokens.slashStarComments(true);
            tokens.slashSlashComments(true);

            tokens.commentChar('#');  
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
                    System.out.println("Number: " + tokens.nval);
                    break;
                case StreamTokenizer.TT_WORD:
                    System.out.println("Identifier: " + tokens.sval);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
