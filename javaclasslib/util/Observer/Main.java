import java.io.*;
import java.util.*;
import java.util.zip.*;

class Main implements Observer {
    Main(File dir, boolean includeDirectories) {
        FileWalker fw = new FileWalker();

        fw.addObserver(this);
        fw.walk(dir, includeDirectories);
    }

    // Simply print out the name of the file.
    public void update(Observable o, Object arg) {
        File f = (File)arg;
        try {
            System.out.println(f.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.err.println("Usage: java Main <directory> [-d]");
            System.exit(1);
        }
        boolean includeDirectories = args.length == 2 && args[1].equals("-d");
        new Main(new File(args[0]), includeDirectories);
    }
}

class FileWalker extends Observable {
    // If includeDirectories is false, the walker does not notify the observers
    // when it encounters a directory. Encountered files are always reported.
    void walk(File dir, boolean includeDirectories) {
        if (dir.isDirectory()) {
            if (includeDirectories) {
                setChanged();
                notifyObservers(dir);
            }
            String[] filenames = dir.list();

            // Recursively walk all subdirectories.
            if (filenames != null) {
                for (int i=0; i<filenames.length; i++) {
                    walk(new File(dir, filenames[i]), includeDirectories);
                }
            }
        } else {
            setChanged();
            notifyObservers(dir);
        }
    }
}
