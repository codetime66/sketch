import java.io.*;
import java.util.zip.*;

class Main {
    // 
    public static void main(String[] args) {
        try {
            ZipFile zis = new ZipFile(args[0]);
        } catch (ZipException e) {
            System.err.println("Not a ZIP file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            ZipOutputStream zos = new ZipOutputStream(
                new FileOutputStream("test"));
            ZipEntry ze = new ZipEntry("test");
            ze.setMethod(ZipEntry.STORED);

            zos.putNextEntry(ze);
        } catch (ZipException e) {
            System.err.println("Failed to write the zip file.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}