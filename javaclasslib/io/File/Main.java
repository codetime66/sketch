import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Main extends Frame implements ActionListener {
    List list = new List();
    File curDir;

    Main(File dir) {
        curDir = dir;

        // Fill list with the directories in the current directory.
        display(null);

        // Listen for events.
        list.addActionListener(this);

        // Setup frame and display.
        setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(list);
        setSize(300, 200);
        show();
    }

    // Fills list with the directories in the directory 'name'.
    void display(String name) {
        String[] filenames;
        if (name == null) {
            filenames = curDir.list();
        } else if (name.equals("..")) {
            curDir = new File(curDir.getParent());
        } else {
            curDir = new File(curDir, name);
        }

        // Get the filenames in curDir.
        filenames = curDir.list();

        if (filenames == null) {
            // This simplifies the subsequent code.
            filenames = new String[]{};
        }

        // Update the frame's title.
        setTitle(curDir.getAbsolutePath());

        for (int i=0; i<filenames.length; i++) {
            File f = new File(curDir, filenames[i]);

            // If f is a directory and has children, append a "+" to the name.
            if (f.isDirectory()) {
                String[] children = f.list();

                if (children != null) {
                    for (int j=0; j<children.length; j++) {
                        if ((new File(f, children[j])).isDirectory()) {
                            filenames[i] += " +";
                            break;
                        }
                    }
                }
            } else {
                filenames[i] = null;
            }
        }

        // Update the list component with the new filenames..
        list.removeAll();
        if (curDir.getParent() != null) {
            list.addItem("..");
        }
        for (int i=0; i<filenames.length; i++) {
            if (filenames[i] != null) {
                list.addItem(filenames[i]);
            }
        }
    }

    public void actionPerformed(ActionEvent evt) {
        String item = list.getSelectedItem();
        
        if (item != null && (item.equals("..") || item.endsWith(" +"))) {
            if (item.endsWith(" +")) {
                item = item.substring(0, item.length()-2);
            }
            // If item is a directory, update the list.
            display(item);
        }
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            File dir = new File(args[0]);

            if (!dir.isDirectory()) {
                System.err.println(args[0] + " is not a directory.");
            } else if (!dir.exists()) {
                System.err.println(args[0] + " does not exist.");
            } else {
                new Main(new File(dir.getAbsolutePath()));
            }
        } else {
            System.err.println("Usage: java Main <directory>");
        }
    }

}
