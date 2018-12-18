import java.io.*;
import java.util.*;
import java.util.zip.*;

class FileWalker extends Observable {
    void walk(File dir, boolean includeDirectories) {
        if (dir.isDirectory()) {
            if (includeDirectories) {
                setChanged();
                notifyObservers(dir);
            }
            String[] filenames = dir.list();

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
