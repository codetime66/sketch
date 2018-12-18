import java.io.*;
import java.util.*;
import java.util.zip.*;

class Main implements Observer {
    ZipOutputStream zos;
    String[] patterns;
    File dir;
    
    Main(String dirname, String outFilename, String[] patterns) {
        dir = new File(dirname);
        this.patterns = patterns;

        try {
            // Create zip output stream.
            zos = new ZipOutputStream(new FileOutputStream(outFilename));
    
            // Start walking the file system.
            FileWalker fw = new FileWalker();
            fw.addObserver(this);
            fw.walk(new File(dirname), false);
    
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method is called for each file that the file walker discovers.
    public void update(Observable o, Object arg) {
        File f = (File)arg;
        byte[] buf = new byte[1024];
        int len;

        try {
            if (match(f.getName(), patterns)) {
                ZipEntry ze = new ZipEntry(convertToZIPName(f));
                FileInputStream is = new FileInputStream(f);
    
                // Initialize entry with the file's last modified time.
                ze.setTime(f.lastModified());

                // These statements are for demonstrative purposes 
                // and not necessary.
                ze.setMethod(ZipEntry.DEFLATED);
                ze.setComment(f.getCanonicalPath());
                ze.setExtra(new byte[]{(byte)'X'});

                // Add the zip entry.
                zos.putNextEntry(ze);

                // Now read and write the zip entry data.
                while ((len = is.read(buf)) >= 0) {
                    zos.write(buf, 0, len);
                }

                // This isn't necessary since the next call to putNextEntry()
                // will close the zip entry.
                zos.closeEntry();

                System.out.println(ze.getName() + " (" 
                    + ze.getCompressedSize()*100/ze.getSize() + "%)");

                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Returns true if s matches one of the patterns.
    boolean match(String s, String[] patterns) {
        if (patterns.length == 0) {
            return true;
        } else {
            for (int i=0; i<patterns.length; i++) {
                if (s.endsWith(patterns[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    // Converts f's pathname to a form acceptable to ZIP files.
    String convertToZIPName(File f) throws IOException {
        String root = dir.getCanonicalPath();
        String pname = f.getCanonicalPath();
        String rootname = 
            root.substring(root.lastIndexOf(File.separatorChar) + 1);

        pname = pname.substring(root.length() + 1);
        pname = pname.replace(File.separatorChar, '/');
        return rootname + "/" + pname;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println(
                "Usage: java Main <directory> <output file> [<pattern1>...]");
        } else {
            // Retrieve patterns, if any.
            String[] patterns = new String[args.length-2];
            for (int i=2; i<args.length; i++) {
                patterns[i-2] = args[i];
            }
            new Main(args[0], args[1], patterns);
        }
    }
}
