import java.io.*;
import java.util.zip.*;

class JavaDeflater extends Deflater {
    public JavaDeflater() {
        super();
        init();
    }
    public JavaDeflater(int level) {
        super(level);
        init();
    }
    public JavaDeflater(int level, boolean noHeader) {
        super(level, noHeader);
        init();
    }

    void init() {
        byte[] buf = dictionary.getBytes();
        setDictionary(buf, 0, buf.length);
    }

    protected void finalize() {
        System.out.println("This deflater instance is no longer being used.");
        end();
    }

    // A dictionary of all Java keywords.
    String dictionary = 
"newbooleanbytecharshortintlongfloatdoublestringvoidsuperprotected"
+"nulltruefalsethisifelseforwhiledoswitchcasedefaultbreakcontinuereturntry"
+"catchfinallythrowimportclassextendsimplementsinterfacepackageprivatepublic"
+"statictransientconstsynchronizednativefinalvolatileabstractthrowslength";
}
