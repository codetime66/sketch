import java.io.*;
import java.net.*;
import java.util.zip.*;

public class Server extends Thread {
    static String dictionary = 
        "byteshortintlongfloatdoubleclassinterfaceforifelse";

    public void run() {
        byte[] inbuf = new byte[128];
        Inflater inf = new Inflater();

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                DataInputStream is = 
                    new DataInputStream(socket.getInputStream());
                byte[] strbuf;

                while (true) {
                    int offset = 0;
                    long dictCRC = -1L;

                    try {
                        strbuf = new byte[is.readInt()];
                    } catch (EOFException e) {
                        // Connection is closed.
                        break;
                    }
    
                    // Continue until all of the string is decompressed.
                    while (!inf.finished()) {
                        // while(inf.getRemaining() == 0) {// could also be used.
                        if (inf.needsInput()) {
                            int len = is.read(inbuf);
                            inf.setInput(inbuf, 0, len);
                        }

                        // Check if the data was compressed with a 
                        // pre-built dictionary.
                        if (inf.needsDictionary()) {
                            dictCRC = inf.getAdler();
                            inf.setDictionary(dictionary.getBytes());
                        }

                        // Retrieve the decompressed string.
                        offset += 
                            inf.inflate(strbuf, offset, strbuf.length-offset);
                    }
    
                    // Print details.
                    System.out.println(new String(strbuf));
                    System.out.print("  in: " + inf.getTotalIn());
                    System.out.println("  " + 
                        (dictCRC < 0 ? "" : "<"+dictCRC+">"));
                    System.out.println("  out: " + inf.getTotalOut());
                    System.out.println("  adler: 0x" + 
                        Integer.toHexString(inf.getAdler()));
                    System.out.println("----------------------------");
                    inf.reset();
                }
            } catch (IOException e) {
                e.printStackTrace();
                inf.reset();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        // Not necessary; shown here for demonstrative purposes.
        inf.end();
    }

    static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            // Create a server socket on port 1200 and start the server.
            serverSocket = new ServerSocket(1200);
            new Server().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
