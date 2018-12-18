package pkg;

import java.awt.*;
import java.io.*;
import java.net.*;

class Main {
    static String getResource(String rsrcName) {
        String result = "";

        try {
            // Get an input stream on the resource.
            InputStream is = Main.class.getResourceAsStream(rsrcName); 
                        // Main[].class also works.
            if (is == null) {
                return null;
            }

            // Convert the input stream into a reader.
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            // Read each line and print it.
            String line;
            while ((line = rd.readLine()) != null) {
                result += line;
            } 
            is.close();
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
    }
}
