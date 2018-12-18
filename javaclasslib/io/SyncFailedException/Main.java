import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("java Main <inputfile> <outputfile>");
            System.exit(-1);
        }
        try {
            FileInputStream in = new FileInputStream(args[0]);
            FileOutputStream out = new FileOutputStream(args[1]);

            byte[] buf = new byte[512];
            int count;
            while ((count = in.read(buf)) > 0) {
                out.write(buf, 0, count);
            }
            in.close();
            out.flush();               // Flush data
//
            try {
                out.getFD().sync();    // Commit changes to disk
            } catch (SyncFailedException se) {
                System.err.println("sync failed: " + se);
            }
//
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
