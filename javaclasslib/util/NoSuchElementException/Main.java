import java.util.Hashtable;
import java.util.Enumeration;

class Main {
    public static void main(String[] args) {
        Hashtable tab = new Hashtable(13);

        tab.put("Jones", "station wagon");
        tab.put("Smith", "race car");
        tab.put("Graham", "sedan");

        Enumeration e = tab.keys();
        Object elem;
        while ((elem = e.nextElement()) != null)
            System.out.println(elem);
    }
}
