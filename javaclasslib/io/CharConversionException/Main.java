import java.io.*;

class Main {
    public static void main(String[] args) {
	try {
	    OutputStreamWriter out = new OutputStreamWriter(System.out);

	    char[] buf = new char[10000];

	    for (int i = 0; i < 10000; i++) {
		buf[i] = (char)i; // put in some random Unicode numbers
	    } 
	    out.write(buf);
	    out.flush();
	    out.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
