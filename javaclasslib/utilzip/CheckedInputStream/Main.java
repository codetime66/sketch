import java.net.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;

class Main implements Runnable {
    Vector urls;
    int period = 60 * 60 * 1000;    // One hour.
    
    Main(Vector urls, String periodStr) {
        this.urls = urls;

        // Get time units.
        char c = periodStr.charAt(periodStr.length()-1);
        double p = Double.valueOf(
            periodStr.substring(0, periodStr.length()-1)).doubleValue();
        
        if (c == 's') {                                 // seconds
            period = (int)(p * 1000);
        } else if (c == 'd') {                          // days
            period = (int)(p * 24 * 60 * 60 * 1000);
        } else if (c == 'h') {                          // hours
            period = (int)(p * 60 * 60 * 1000);
        } else if (c == 'm') {                          // minutes
            period = (int)(p * 60 * 1000);
        }

        (new Thread(this)).start();
    }

    public void run() {
        Adler32 adler = new Adler32();
        Properties prop = getChecksums();

        while (true) {
            try {
                for (int i=0; i<urls.size(); i++) {
                    URL url = new URL((String)urls.elementAt(i));
                    CheckedInputStream is = 
                        new CheckedInputStream(
                        new BufferedInputStream(url.openStream()), adler);

                    // Use skip().
                    while (is.skip(100000) > 0) {
                    } 

                    // Or use read().
                    //byte[] buf = new byte[1024];
                    //while (is.read(buf, 0, buf.length) >= 0) {
                    //}
                    is.close();

                    // Compare the current checksum with the previous checksum.
                    String s = (String)prop.get(
                        URLEncoder.encode(url.toString()));
                    if (s == null 
                        || !s.equals(""+is.getChecksum().getValue())) {
                        System.out.println(
                            url.toString() + " has been updated.");
                    }
                    prop.put(URLEncoder.encode(url.toString()), 
                        ""+is.getChecksum().getValue());

                    // Reset since it will be used again.
                    adler.reset();
                }
                // Save the latest set of checksums.
                saveChecksums(prop);
                
                // Sleep for the specified period.
                Thread.sleep(period);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Read and return the checksum values.
    Properties getChecksums() {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("urls.properties");
            prop.load(fis);
            fis.close();
        } catch (IOException e) {
        }
        return prop;
    }

    // Save and return the checksum values.
    void saveChecksums(Properties prop) {
        FileInputStream fis;
        Properties properties = new Properties();
        try {
            FileOutputStream fos = new FileOutputStream("urls.properties");
            prop.save(fos, "Checksum of URLs");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Main <file of URLs> <Ns|Nm|Nh|Nd>");
            System.exit(1);
        }

        // Read file of URLs
        Vector urls = new Vector();
        try {
            // Create the input stream.
            BufferedReader is = new BufferedReader(new FileReader(args[0]));

            // Read the input stream.
            String line = null;
            while ((line = is.readLine()) != null) {
                urls.addElement(line);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Main(urls, args[1]);
    }
}
