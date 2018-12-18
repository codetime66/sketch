import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            FileInputStream is = new FileInputStream("data.properties");
    
            PropertyResourceBundle bundle = new PropertyResourceBundle(is);
            is.close();
            
            System.out.println(bundle.getString("key-1"));
            System.out.println(bundle.getString("key-2"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}