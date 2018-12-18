import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;

class Main extends Frame implements ActionListener {
    String zipFilename;
    ZipFile zipfile;

    // Vector of zip entries.
    Vector entries = new Vector();

    // Components.
    List entryList = new List();
    Button removeBtn = new Button("Remove");
    Button saveBtn = new Button("Save and Exit");
    Button openBtn = new Button("Open");
    
    Main(String zipFilename) throws IOException {
        this.zipFilename = zipFilename;
        zipfile = new ZipFile(zipFilename);
        setTitle("JavaZip " + zipfile.getName());

        // Get the entries into a vector.
        for (Enumeration e=zipfile.entries(); e.hasMoreElements();) {
            entries.addElement((ZipEntry)e.nextElement());
        }

        // Sort the vector.
        QuickSort.sort(entries, new QuickSortCompare());

        // Add sorted entries to list component.
        for (int i=0; i<entries.size(); i++) {
            ZipEntry ze = (ZipEntry)entries.elementAt(i);
            entryList.add(ze.getName());
        }

        // Layout components.
        entryList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(entryList, BorderLayout.CENTER);

        // Set up the buttons along the bottom.
        Panel p = new Panel(new FlowLayout());
        p.add(openBtn);
        p.add(removeBtn);
        p.add(saveBtn);
        add(p, BorderLayout.SOUTH);

        // Listen for events.
        openBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        addWindowListener(new WindowEventHandler());

        setSize(300, 400);
        show();
    }
    
    class QuickSortCompare implements Comparator {
        public int compare(Object o1, Object o2) {
            return ((ZipEntry)o1).getName().
                     compareTo(((ZipEntry)o2).getName());
        }
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
            System.exit(1);
        }
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == removeBtn) {
            int sel = entryList.getSelectedIndex();
            if (sel >= 0) {
                entryList.delItem(sel);
                entries.removeElementAt(sel);
            }
        } else if (evt.getSource() == saveBtn) {
            try {
                // First save the new zip file in a file called "temp".
                File outfile = new File("temp");
                ZipOutputStream os = 
                    new ZipOutputStream(new FileOutputStream(outfile));

                // Write all remaining entries to the new zip file.
                for (int i=0; i<entries.size(); i++) {
                    ZipEntry ze = (ZipEntry)entries.elementAt(i);
                    InputStream is = zipfile.getInputStream(ze);
    
                    // Status information.
                    System.out.println(ze.getName());
                    
                    // Copy data from input stream to output stream.
                    os.putNextEntry(new ZipEntry(ze.getName()));
                    os.putNextEntry(ze);
                    copy(is, os);
                    is.close();
                }

                os.close();
                zipfile.close();

                // Now rename the temp file to the original file.
                outfile.renameTo(new File(zipFilename));
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (evt.getSource() == openBtn) {
            int sel = entryList.getSelectedIndex();
            if (sel >= 0) {
                try {
                    ZipEntry ze = (ZipEntry)entries.elementAt(sel);
                    byte[] buf = new byte[(int)ze.getSize()];
                    InputStream is = zipfile.getInputStream(ze);
                    int len = 0, off = 0;
    
                    // Read the entire contents.
                    while (off < buf.length 
                            && (len = is.read(buf, off, buf.length-off)) >= 0) {
                        off += len;
                    }
                    is.close();
                    new TextViewer(new String(buf), ze.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Copy data from is to os.
    void copy(InputStream is, OutputStream os) throws IOException {
        byte[] buf = new byte[1024];
        int len;

        while ((len = is.read(buf)) >= 0) {
            os.write(buf, 0, len);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <zip filename>");
        } else {
            try {
                new Main(args[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// This frame is used to view the decompressed data of a zip entry.
class TextViewer extends Frame {
    TextViewer(String text, String name) {
        super(name);

        // Create and add the text area.
        TextArea ta = new TextArea(text);
        add(ta, BorderLayout.CENTER);

        // Listen for events and show frame.
        addWindowListener(new WindowEventHandler());
        setSize(300, 300);
        show();
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent evt) {
            // Destroy the window.
            dispose();
        }
    }
}
