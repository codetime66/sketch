import java.util.Hashtable;
import java.util.Enumeration;

class Main {
    public static void main(String[] args) {
        Hashtable jukebox = new Hashtable(13, 0.5f);

        jukebox.put("Hound Dog", new Disc("Hound Dog", "Elvis"));
        jukebox.put("Yesterday", new Disc("Yesterday", "Beatles"));

        // Make a copy of it
        Hashtable oldies = (Hashtable)jukebox.clone();

        // find houndDog in jukebox
        System.out.println("looking for hounddog: " + 
                           jukebox.get("Hound Dog"));
        System.out.println("removing it: " + 
                           jukebox.remove("Hound Dog"));
        System.out.println("looking for it again: " + 
                           jukebox.get("Hound Dog"));

        // find houndDog in oldies (should still be there)
        System.out.println("looking for hounddog in oldies: " +
                           oldies.get("Hound Dog"));
    }
}
