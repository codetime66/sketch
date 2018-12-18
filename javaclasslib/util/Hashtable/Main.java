import java.util.Hashtable;
import java.util.Enumeration;

class Disc {
    public String title;
    public String singer;

    Disc(String t, String s) {
        title = t;
        singer = s;
    }
    public String toString() {
        return ("'" + title + "' by " + singer);
    }
}
class Main {
    // print the contents of the jukebox
    public static void print(String msg, Hashtable box, boolean all) {
        if (msg != null)
            System.out.print(msg + ": ");
        if (box.isEmpty())
            System.out.println("The juke box is empty");
        else {
            System.out.println("There are " + box.size() 
                               + " discs in the juke box:");
            for(Enumeration e = (all ? box.elements() : box.keys());
                e.hasMoreElements(); 
                System.out.println("\t" + e.nextElement()));
        }
    }
    public static void main (String[] args) {
        // create a jukebox with initial capacity of 13 and 0.5 load factor
        Hashtable jukebox = new Hashtable(13, 0.5f);
        Disc houndDog;

        jukebox.put("Hound Dog", houndDog = new Disc("Hound Dog", "Elvis"));
        jukebox.put("Yesterday", new Disc("Yesterday", "Beatles"));
        jukebox.put("On Top of the World", 
                    new Disc("On Top of the World", "Carpenters"));
        jukebox.put("Only You", new Disc("Only You", "Platters"));
    
        print("jukebox after adding 4 titles", jukebox, true);

        // search by title
        System.out.println("Yesterday is " +
            (jukebox.containsKey("Yesterday") ? "" : "not ") +
            " in the jukebox");
        // search by content
        System.out.println(houndDog + " is " +
            (jukebox.contains(houndDog) ? "" : "not ") +
            " in the jukebox");

        // empty jukebox
        jukebox.clear();
        print("jukebox after clearing it", jukebox, true);
    }
}
