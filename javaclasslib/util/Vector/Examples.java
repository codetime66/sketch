import java.util.Vector;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/*
// START capinc
public final synchronized void ensureCapacity(int minCapacity) {
    int oldCapacity = elementData.length;
    if (minCapacity > oldCapacity) {
	Object oldData[] = elementData;
	int newCapacity = (capacityIncrement > 0) ?
	    (oldCapacity + capacityIncrement) : (oldCapacity * 2);
	if (newCapacity < minCapacity) {
	    newCapacity = minCapacity;
	}
	elementData = new Object[newCapacity];
	System.arraycopy(oldData, 0, elementData, 0, elementCount);
    }
}
// END capinc
*/

class Examples {
// START insert
public static boolean replaceElementWithVec(Vector vec, Object obj, 
					    Vector replacement)
{
    int ind = vec.indexOf(obj);
    if (ind < 0)
	return (false);
    
    // Remove existing element and insert vector
    vec.removeElementAt(ind);
    for(int i = 0; i < replacement.size(); i++)
	vec.insertElementAt(replacement.elementAt(i), ind++);
    return (true);
}
// END insert
// START first
public static void swapFirstLast(Vector vec) {
    try {
	Object fst = vec.firstElement();
	Object lst = vec.lastElement();
	vec.setElementAt(lst, 0);
	vec.setElementAt(fst, vec.size()-1);
    } catch (NoSuchElementException e) {
	System.out.println(e);
    }
}
// END first
// create a new vector that is a copy of the first n entries of a vector
// START setSize
public static Vector vecNCopy(Vector orig, int n) {
    Vector vec = (Vector)orig.clone();
    vec.setSize(n);
    return (vec);
}
// END setSize
// replace first occurrence of target with replacement
// START indexof
public static boolean replaceFirst(Vector vec, Object target, Object sub) {
    int loc = vec.indexOf(target);
    if (loc >= 0) {
	vec.setElementAt(sub, loc);
	return (true);
    }
    return (false);
}
// END indexof
// START lastof
public static boolean replaceLast(Vector vec, Object target, Object sub) {
    int loc = vec.lastIndexOf(target);
    if (loc >= 0) {
	vec.setElementAt(sub, loc);
	return (true);
    }
    return (false);
}
// END lastof
// START copy
public static String[] filter(String[] master, 
			      String[] additions,
			      String[] deletions) {
    // make rough estimate of size needed
    Vector vec = 
	new Vector(master.length+additions.length-deletions.length);
    // 
    for (int i = 0; i < master.length; i++)
	vec.addElement(master[i]);
    
    for (int i = 0; i < deletions.length; i ++)
	vec.removeElement(deletions[i]);
    
    for (int i = 0; i < additions.length; i++)
	if (!vec.contains(additions[i]))
	vec.addElement(additions[i]);
    
    String[] newmaster = new String[vec.size()];
    vec.copyInto((Object[])newmaster);
    return (newmaster);
}
// END copy
// START elemat
public static void printVec(String msg, Vector vec) {
    if (msg != null)
	System.out.println(msg);
    if (vec.isEmpty())
	System.out.println("Empty vector");
    else
	for (int i  = 0; i < vec.size(); i++)
	    System.out.println(vec.elementAt(i));
}
// END elemat
// clear vector and remove unused space
// START trim
public static void clearVec(Vector vec) {
    vec.removeAllElements();	// remove all elements
    vec.trimToSize();		// reclaim space
}
// END trim
    public static void main (String[] args) {
	String[] adds = {"apple", "orange"};
	String[] dels = {"chips", "candy"};

	String[] filtered = filter(args, adds, dels);

	for (int i = 0; i < filtered.length; i++)
	    System.out.println("\t" + filtered[i]);
// START str
Vector v = new Vector(10);
v.addElement("a");
v.addElement("b");
v.addElement("c");
v.addElement("d");
v.addElement("e");
v.addElement("f");
System.out.println(v.toString());  // [a, b, c, d, e, f]
// END str
	printVec("before swap", v);
	swapFirstLast(v);
	printVec("after swap", v);

	Vector v2 = new Vector(10);
	v2.addElement(new Integer(1));
	v2.addElement(new Integer(2));
	v2.addElement(new Integer(3));
	v2.addElement(new Integer(4));

	replaceElementWithVec(v, "e", v2);
	printVec("after replacing e", v);

	Vector v3 = vecNCopy(v, 5);
	printVec("v3", v3);

	replaceFirst(v3, "c", "C");
	replaceLast(v3, "b", "B");
	printVec("v3 after replace", v3);

	clearVec(v3);
	printVec("v3 after clear", v3);

// START cap
if (v.capacity() < 100)
    v.ensureCapacity(150);
// END cap
    }
}
