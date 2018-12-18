import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.zip.*;

class Main extends Frame {
    int cellSize = 125;

    Main(File dir) {
        String[] filenames;

        if (dir.isDirectory()) {
            // If directory, get files.
            filenames = dir.list();
        } else {
            // Otherwise, only show one image.
            filenames = new String[1];
            filenames[0] = dir.getName();

            // Set dir to be the directory rather than the file.
            dir = new File(dir.getParent());
        }
    
        for (int i=0; i<filenames.length; i++) {
            if (filenames[i].toLowerCase().endsWith(".gif")
                || filenames[i].toLowerCase().endsWith(".jpg")) {

                // Create and add the thumbnail component.
                // File is relative to dir.
                Component c = new Thumbnail(new File(
                    dir.getAbsolutePath() + File.separator + filenames[i]));
                add(c);
                c.setSize(cellSize, cellSize);
            }
        }

        // Listen for events and layout.
        addWindowListener(new WindowEventHandler());
        setLayout(new FlowLayout());
        pack();
        show();
    }

    public Dimension getPreferredSize() {
        Insets insets = getInsets();
        int count = getComponentCount();
        int hgap = ((FlowLayout)getLayout()).getHgap();
        int vgap = ((FlowLayout)getLayout()).getVgap();

        // Maximum of 4 columns,
        int cols = Math.max(1, Math.min(count, 4));

        // The following code exactly determines the size
        // necessary to show all the thumbnails.
        Dimension d = new Dimension(
            cols*(cellSize+hgap) + hgap, 
            ((count-1)/cols+1)*(cellSize+vgap) + vgap);

        // Don't forget the frame's insets.
        d.width += insets.left+insets.right;
        d.height += insets.top+insets.bottom;

        // Make sure it's not larger than the screen.
        Dimension screenDim = getToolkit().getScreenSize();
        d.width = Math.min(d.width, screenDim.width);
        d.height = Math.min(d.height, screenDim.height);
        return d;
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent evt) {
            // Destroy the window.
            dispose();
        }
    }

    public static void main(String[] args) {
        File dir;

        if (args.length == 1) {
            dir = new File(args[0]);
        } else {
            dir = new File(".");
        }
        new Main(dir);
    }
}

class Thumbnail extends Canvas {
    File fullFile;
    Image fullImage;
    File thumbFile;
    Image thumbImage;
    String filename;
    boolean saveThumbnail;

    Thumbnail(File file) {
        fullFile = file;
        thumbFile = new File(file.getPath() + ".imgz");

        if (thumbFile.exists() 
                && thumbFile.lastModified() > file.lastModified()) {
// -------------- Read compressed image ---------------
            System.out.print("Reading thumbnail " + thumbFile.getName() + 
                             " ... ");
            try {
                ObjectInputStream is = new ObjectInputStream(
                    new GZIPInputStream(
                    new FileInputStream(thumbFile)));

                // Read the dimensions.
                Dimension dim = (Dimension)is.readObject();

                // Read the pixels into a pixel array.
                int[] pixels = (int[])is.readObject();

                // Convert the pixels into an image.
                thumbImage = getToolkit().createImage(
                    new MemoryImageSource(dim.width, dim.height, pixels, 
                                          0, dim.width));
                System.out.println("done");
            } catch (Exception e) {
                System.out.println("failed");
            }
// ------------------------------------------------------
        } 
        if (thumbImage == null) {
            fullImage = getToolkit().getImage(file.getPath());
            saveThumbnail = true;
        }

        // Listen for mouse events.
        addMouseListener(new MouseEventHandler());
    }

    public void paint(Graphics g) {
        update(g);
    }
    public void update(Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int w = getSize().width;
        int h = getSize().height - fm.getHeight();
        int iw = 0;
        int ih = 0;

        // Create the thumbnail if it doesn't exist.
        if (thumbImage == null) {
            iw = fullImage.getWidth(this);
            ih = fullImage.getHeight(this);

            if (iw > 0 && ih > 0) {
                // Scale down if necessary.
                if (iw > h) {
                    ih = ih * w / iw;
                    iw = w;
                }
                if (ih > h && ih > 0) {
                    iw = iw * h / ih;
                    ih = h;
                }

                thumbImage = fullImage.getScaledInstance(iw, ih, 0);
            }
        } else {
            iw = thumbImage.getWidth(null);
            ih = thumbImage.getHeight(null);
        }
        if (iw > 0 && ih > 0) {
            // Clear background.
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, w, h);

            // Center the image.
            boolean done = 
                g.drawImage(thumbImage, (w-iw)/2, (h-ih)/2, iw, ih, this);
            if (done & saveThumbnail) {
// -------------- Write compressed image ---------------
                System.out.print("saving " + thumbFile.getName() + " ... ");
                try {
                    // Grab the pixels from the image.
                    ObjectOutputStream os = new ObjectOutputStream(
                        new GZIPOutputStream(
                        new FileOutputStream(thumbFile)));

                    // Write the dimensions of the image.
                    os.writeObject(new Dimension(iw, ih));

                    // Grab the image pixels and write them out.
                    int[] pixels = new int[iw * ih];
                    PixelGrabber pg = 
                       new PixelGrabber(thumbImage, 0, 0, iw, ih, pixels, 0, iw);
                    pg.grabPixels();
                    os.writeObject(pixels);

                    os.close();
                    System.out.println("done");
                } catch (Exception e) {
                    System.out.println("failed");
                }
// ------------------------------------------------------
                saveThumbnail = false;
            }
        }
        // Draw the name.
        h = getSize().height;
        g.setColor(Color.black);
        g.clearRect(0, h, w, fm.getHeight());
        g.drawString(fullFile.getName(), 
            (w-fm.stringWidth(fullFile.getName()))/2, 
            h-fm.getHeight()+fm.getAscent());
        g.drawRect(0, 0, w-1, h-1);
    }

    class MouseEventHandler extends MouseAdapter {
        public void mousePressed(MouseEvent evt) {
            if (fullImage == null) {
                fullImage = getToolkit().getImage(fullFile.getPath());
            }
            new ImageViewer(fullImage, fullFile.getName());
        }
        public void mouseEntered(MouseEvent evt) {
            // Set the frame's title.
            findFrame().setTitle(filename);
        }
        public void mouseExited(MouseEvent evt) {
            // Clear the frame's title.
            findFrame().setTitle(null);
        }

        // Returns this component's frame.
        public Frame findFrame() {
            Component c = getParent();
            while (c != null && !(c instanceof Frame)) {
                c = c.getParent();
            }
            return (Frame)c;
        }
    }
}

class ImageViewer extends Frame {
    Image image;
    ImageViewer(Image image, String filename) {
        super(filename);
        this.image = image;

        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(image, 0);
            tracker.waitForAll(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Listen for events and show frame.
        addWindowListener(new WindowEventHandler());
        pack();    
        show();
    }

    // Determine the size of the frame that will show the image.
    public Dimension getPreferredSize() {
        Insets insets = getInsets();

        return new Dimension(image.getWidth(null) + insets.left + insets.right,
            image.getHeight(null) + insets.top + insets.bottom);
    }

    public void paint(Graphics g) {
        g.drawImage(image, getInsets().left, getInsets().top, this);
    }

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent evt) {
            // Destroy the window.
            dispose();
        }
    }
}
