import java.io.*;

class Main {
    // writes out an HTML anchor:    <A HREF="ref"> desc </A>
    static CharArrayWriter buf = new CharArrayWriter();
    static String prefix = "<A HREF=\"";
    static String suffix = " </A>\n";

    public static void anchor(String[] desc, String[] ref, Writer out)
    throws IOException{

        for (int a = 0; a < desc.length; a++) {
                buf.write(prefix, 0, prefix.length());
                buf.write(ref[a], 0, ref[1].length());

                buf.write("\"> ", 0, 3);
                buf.write(desc[a], 0, desc[a].length());

                buf.write(suffix, 0, suffix.length());
                buf.writeTo(out);
                buf.reset();        // reset stream to start of buffer
        }
    }
    public static void main(String[] args) {
        String[] desc = {"Preface", "Table of Contents",
                         "Index", "Glossary"};
        String[] ref = {"preface.htm", "toc.htm", 
                        "index.htm", "glossary.htm"};
        Writer out = new OutputStreamWriter(System.out);
        try {
            anchor(desc, ref, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
