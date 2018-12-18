import java.io.*;
import java.net.*;
import java.util.zip.*;

class Main {
    static String dictionary = 
        "byteshortintlongfloatdoubleclassinterfaceforifelse";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1200);
            BufferedReader in = 
		new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream os = 
                new DataOutputStream(socket.getOutputStream());
            String line;
            int count = 0;

            while ((line = in.readLine()) != null) {
                Deflater def = new Deflater();
                DeflaterOutputStream dos = new DeflaterOutputStream(os, def);

                // Alternately set the dictionary to see its effects.
                if ((count++ % 2) == 0) {
                    def.setDictionary(dictionary.getBytes());
                    System.out.println("checksum of dictionary: " + 
                                       def.getAdler());
                }

                // Write the length of the string.
                os.writeInt(line.length());

                // Write the string.
                dos.write(line.getBytes());

                // Finish it but don't close it.
                dos.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
