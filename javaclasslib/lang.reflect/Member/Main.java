import java.lang.reflect.*;
import java.util.*;

class Main {
    Main(Class c) {
        Member[] members;
        Vector v = new Vector();
        
        // Retrieve the members of class c and all its superclasses.
        while (c != null) {
            members = c.getDeclaredFields();
            for (int i=0; i<members.length; i++) {
                v.addElement(members[i]);
            }

            members = c.getDeclaredMethods();
            for (int i=0; i<members.length; i++) {
                v.addElement(members[i]);
            }

            members = c.getDeclaredConstructors();
            for (int i=0; i<members.length; i++) {
                v.addElement(members[i]);
            }
            c = c.getSuperclass();
        }

        // Keep only public and protected members and constructors.
        for (int i=v.size()-1; i>=0; i--) {
            int mods = ((Member)v.elementAt(i)).getModifiers();

            if (!Modifier.isPublic(mods) && !Modifier.isProtected(mods)) {
                v.removeElementAt(i);
            }
        }

        // Transfer the vector contents to an array.
        members = new Member[v.size()];
        for (int i=0; i<v.size(); i++) {
            members[i] = (Member)v.elementAt(i);
        }

        // Sort the array by member name.
        QuickSort.sort(members, new Compare());

        // Print the results.
        String lastEntry = null;
        for (int i=0; i<members.length; i++) {
            String name = members[i].getName();
            String newEntry = members[i].getDeclaringClass()+name;

            // Skip duplicate entries.
            if (newEntry.equals(lastEntry)) {
                continue;
            }
            lastEntry = newEntry;

            System.out.print(name);
            if (!(members[i] instanceof Field)) {
                System.out.print("()");
            }  else {
                System.out.print("  ");
            }

            // Pad with spaces.
            for (int j=0; j<20-name.length(); j++) {
                System.out.print(" ");
            }

            // Print declaring class.
            System.out.println(" " + members[i].getDeclaringClass().getName());
        }
    }

    // Sort by member name.
    class Compare implements Comparator {
        public int compare(Object a, Object b) {
            String s1 = ((Member)a).getName();
            String s2 = ((Member)b).getName();
            return s1.compareTo(s2);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <classname>");
        } else {
            try {
                new Main(Class.forName(args[0]));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
