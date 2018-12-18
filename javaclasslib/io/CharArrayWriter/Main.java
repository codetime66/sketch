import java.io.CharArrayWriter;

class Main {
    // for any meta character that appear in the string 's',
    // escape it with the backslash.
    public static String encode(String s, char[] metachars) {
        // start off with length of string; stream will grow automatically
        CharArrayWriter out = new CharArrayWriter(s.length());

        for (int i = 0; i < s.length(); i++) {
            int c = (int)s.charAt(i);
            for (int j = 0; j < metachars.length; j++) {
                if (c == metachars[j]) {
                    out.write('\\');
                    break;
                }
            }
            out.write(c);
        }
        return out.toString();
    }
    public static void main(String[] args) {
        char[] meta = {'\\', '\''};
        String raw = "'abc'\\b2+3";
        String answer = encode(raw, meta);

        System.out.println("Raw: " + raw);
        System.out.println("Encoded: " + answer);
    }
}
