import java.awt.Point;

// A cloneable Point object
class CPoint extends Object implements Cloneable {
    public Point p;
    public CPoint(int x, int y) {
	super();
	p = new Point(x, y);
    }
    // this clone method makes a copy of the Point object p.
    // Otherwise, modifications to p of the original object
    // would affect the clone as well.
    public Object clone() throws CloneNotSupportedException {
	CPoint pobj = (CPoint)super.clone();
	// Create a new point with initial values same as this obj
	pobj.p = new Point(p.x, p.y);		// assign to clone
	return ((Object)pobj);
    }
    public String toString() {
	return (p.toString());
    }
}

class Main {
    public static void main(String[] args) {
	CPoint orig = new CPoint(3, 4);
	System.out.println("orig: " + orig); // 3, 4

	try {
	    CPoint cpy = (CPoint)orig.clone();
	    System.out.println("cpy: " + cpy);
	    // alter orig's point, but cpy's point is not affected
	    orig.p.move(10, 20);
	    System.out.println("orig: " + orig); // 10, 20
	    System.out.println("cpy: " + cpy); // unchanged. 3, 4
	} catch (CloneNotSupportedException e) {
	    System.out.println(e);
	}
    }
}

