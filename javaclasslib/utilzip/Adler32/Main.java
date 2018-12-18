import java.io.*;
import java.util.*;
import java.util.zip.*;

class Main implements Observer {
    // Use instance variable so that we can reuse it by calling reset().
    Adler32 checksum = new Adler32();

    Main(File dir) {
        FileWalker fw = new FileWalker();

        fw.addObserver(this);
        fw.walk(dir, false);
    }

    // This method is called for each file that the file walker discovers.
    public void update(Observable o, Object arg) {
        File f = (File)arg;
        checksum.reset();
        try {
            BufferedInputStream is = new BufferedInputStream(
                new FileInputStream(f));
            byte[] bytes = new byte[1024];
            int len = 0;

            // Read the file and compute the checksum.
            while ((len = is.read(bytes)) >= 0) {
                checksum.update(bytes, 0, len);
            }

            // Print the filename and checksum.
            System.out.println(f.getCanonicalPath() 
                + " (" + checksum.getValue() + ")");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <directory>");
        } else {
            new Main(new File(args[0]));
        }
    }
}

class FileWalker extends Observable {
    void walk(File dir, boolean includeDirectories) {
        if (dir.isDirectory()) {
            if (includeDirectories) {
                // Notify the observers.
                setChanged();
                notifyObservers(dir);
            }
            String[] filenames = dir.list();

            // Visit each file in this directory.
            for (int i=0; i < filenames.length; i++) {
                walk(new File(dir, filenames[i]), includeDirectories);
            }
        } else {
            setChanged();
            notifyObservers(dir);
        }
    }
}
