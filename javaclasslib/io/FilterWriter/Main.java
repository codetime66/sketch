import java.io.*;

class LineNumberWriter extends FilterWriter {
    private int linenumber = 0;
    private boolean neednewline = true;
    public LineNumberWriter (Writer out) {
        super(out);
    }

    protected void newline() {
        synchronized (lock) {
            String prefix = ++linenumber + "\t";
            try {
                out.write(prefix);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public void write(int b) throws IOException {
        synchronized (lock) {
            if (neednewline) {
                newline();
                neednewline = false;
            }
            out.write(b);
            if (b == '\n') {
                neednewline = true;
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("java Main <input file>");
            System.exit(-1);
        }
        try {
            FileReader in = new FileReader(args[0]);
            LineNumberWriter out = 
                new LineNumberWriter(new OutputStreamWriter(System.out));

            for (int c = in.read(); c > -1; c = in.read())
                out.write(c);
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
