package pkg;

import java.awt.*;
import java.io.*;
import java.net.*;

class Main {
    static String getResource(String rsrcName) {
        String result = "";

        try {
            // Find the text file.
            URL url = 
                Main.class.getResource(rsrcName); // Main[].class also works.
            if (url == null) {
                return null;
            }

            // Read the text file.
            BufferedReader rd = new BufferedReader(
                new InputStreamReader(url.openStream()));

            // Read each line and print it.
            String line;
            while ((line = rd.readLine()) != null) {
                result += line;
            } 
            rd.close();
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(getResource("/input.txt"));          
                                              // The Java Class Libraries.
        System.out.println(getResource("input.txt"));           
                                              // null

        System.out.println(getResource("resource.txt"));        
                                              // Have a nice day.
        System.out.println(getResource("/pkg/resource.txt"));   
                                              // Have a nice day.
        System.out.println(getResource("/pkg.resource.txt"));   
                                              // null
        System.out.println(getResource("/resource.txt"));       
                                              // null

        System.out.println(getResource("subpkg/data.txt"));     
                                              // Humpty Dumpty.
        System.out.println(getResource("/pkg/subpkg/data.txt"));
                                              // Humpty Dumpty.
        System.out.println(getResource("data.txt"));            
                                              // null

        // Read an image
        try {
            URL url = Main.class.getResource("duke.gif");
            Object o = url.getContent();

            // Determine what type of object is returned.
            Image image = null;
            if (o instanceof Image) {
                image = (Image)o;
            } else if (o instanceof sun.awt.image.FileImageSource) {
                image = Toolkit.getDefaultToolkit().createImage(
                    new sun.awt.image.URLImageSource(url));
            }
            System.out.println(image);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
