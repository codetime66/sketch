import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.zip.*;

public class SerImage implements Serializable {
    transient Image image;

    public SerImage(String filename) {
        image = Toolkit.getDefaultToolkit().getImage(filename);

        // Create media tracker with dummy button
        MediaTracker tracker = new MediaTracker(new Button());
        try {
            tracker.addImage(image, 0);
            tracker.waitForAll(0);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not create image.");
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        Dimension d = new Dimension(image.getWidth(null), 
            image.getHeight(null));
        DeflaterOutputStream dos = new DeflaterOutputStream(out);
        ObjectOutputStream os = new ObjectOutputStream(dos);

        // Write the dimensions of the image.
        os.writeObject(d);

        // Grab the image pixels.
        int[] pixels = new int[d.width * d.height];
        PixelGrabber pg = new PixelGrabber(
            image, 0, 0, d.width, d.height, pixels, 0, d.width);
        try {
            pg.grabPixels();
        } catch (Exception e) {
            throw new IOException("Could not grab pixels.");
        }

        // Write the pixel data.
        os.writeObject(pixels);

        dos.finish();
    }

    private void readObject(ObjectInputStream in) 
            throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(
            new InflaterInputStream(in));

        // Read the Dimensions.
        Dimension d = (Dimension)is.readObject();

        // Read the pixels into a pixel array.
        int[] pixels = (int[])is.readObject();

        // Convert the pixels into an image.
        image = Toolkit.getDefaultToolkit().createImage(
            new MemoryImageSource(d.width, d.height, pixels, 0, d.width));
    }
}
