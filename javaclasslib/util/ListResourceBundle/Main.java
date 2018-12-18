import java.util.*;

class Main {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("MyListResources");

        System.out.println(bundle.getString("key-1"));            // wombat
        System.out.println(bundle.getStringArray("key-2")[0]);    // apple
        System.out.println(bundle.getObject("key-3"));
                                                // java.awt.Point[x=1,y=2]
    }
}
