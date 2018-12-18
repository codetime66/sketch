import java.awt.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <imagefile>...");
            System.exit(1);
        }

        for (int i=0; i<args.length; i++) {
            try {
                ObjectInputStream is = new ObjectInputStream(
                    new FileInputStream(args[i]));
    
                System.out.println("Reading " + args[i]);
                SerImage si = (SerImage)is.readObject();
                is.close();

                new ImageViewer(si.image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class ImageViewer extends Frame {
    Image image;
    ImageViewer(Image image){ 
        this.image = image;
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
}
