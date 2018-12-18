import java.io.PushbackInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;

class Main {
    static final int CLASSC_LIMIT = 100;
    static final int CLASSA = 1;
    static final int CLASSB = 2;
    static void classAParser(InputStream out) {
    }
    static void classBParser(InputStream out) {
    }
    static void classCParser(InputStream out) {
    }
    public static void main(String[] args) {
	if (args.length != 1) {
	    System.err.println("Usage: java Main2 <file>");
	    System.exit(-1);
	}
{
// START mark
PushbackInputStream pushin = new PushbackInputStream(System.in);
if (pushin.markSupported())
    System.out.println("Mark for pushback is supported");
else 
    System.out.println("Mark for pushback is not supported");
// END mark
}

// START avail
try {
    PushbackInputStream pushin = 
	new PushbackInputStream(new FileInputStream(args[0]));
    if (pushin.available() > CLASSC_LIMIT) {
	// read header (first byte) to find out format
	int c = pushin.read();
	if (c >= 0)
	    pushin.unread(c);  // let parsers deal with it
	switch (c) {
	case CLASSA:
	    classAParser(pushin);
	    break;
	case CLASSB:
	    classBParser(pushin);
	}
    } else {
	classCParser(pushin);
    }
    pushin.close();
} catch (IOException e) {
    e.printStackTrace();
}
// END avail
    }
}
	    
