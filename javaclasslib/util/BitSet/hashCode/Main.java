import java.util.BitSet;

class Main {
    public static void main(String[] args) {
// START hash
BitSet bs = new BitSet(128);
// UNCOM...
int[] hits = new int[13];
int hashval = bs.hashCode();	       // generate hash code
++hits[Math.abs(hashval%hits.length)]; // count hits
// END hash
System.out.println(hashval);
System.out.println(hits[Math.abs(hashval%hits.length)]); // count hits
    }
}
