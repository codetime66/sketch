import java.util.Hashtable;

class Main {
/*
// START equals
public class Hashtable extends Dictionary implements Cloneable {
   ...
    public synchronized Object get(Object key) {
	HashtableEntry tab[] = table;
	int hash = key.hashCode();
	int index = (hash & 0x7FFFFFFF) % tab.length;
	for (HashtableEntry e = tab[index] ; e != null ; e = e.next) {
	    if ((e.hash == hash) && e.key.equals(key)) {
		return e.value;
	    }
	}
	return null;
    }
}
// END equals
*/
// START getclass
public static void printClassName(Object obj) {
    System.out.println("The class of " + obj + " is " +
		       obj.getClass().getName());
}
// END getclass
    public static void main(String[] args) {
Long lobj = new Long(1283);
printClassName(lobj);
/*
{
// START notify
// wait until all workers are idle
while (workers_active > 0) {
    try {
	wait();
    } catch (InterruptedException e) {
	// ignore
    }
}
fillTaskList();
notifyAll();
// END
}
*/
// START str
Object obj = new Hashtable();

// the toString() invoked
// will be the overridden toString() defined by Hashtable
System.out.println(obj.toString());
// END str
}
}
