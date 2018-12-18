import java.util.*;

class QuickSort {
    // Main sort routine.
    public static void sort(Vector v, Comparator cmp) {
        sort(v, 0, v.size() - 1, cmp);
    }

    // Internal recursive routine.
    private static void sort(Vector v, int lo0, int hi0, Comparator cmp) {
        int lo = lo0;
        int hi = hi0;
        Object mid;

        if (hi0 > lo0) {
            mid = v.elementAt((lo0 + hi0 ) / 2);

            // Loop through the array until indices cross.
            while(lo <= hi) {
                while( (lo < hi0) && cmp.compare(v.elementAt(lo), mid) < 0) {
                    ++lo;
                }

                while(hi > lo0 && cmp.compare(v.elementAt(hi), mid) > 0) {
                    --hi;
                }

                // if the indexes have not crossed, swap
                if(lo <= hi) {
                    swap(v, lo, hi);
                    ++lo;
                    --hi;
                }
            }

            if(lo0 < hi) {
                sort(v, lo0, hi, cmp);
            }

            if(lo < hi0) {
                sort(v, lo, hi0, cmp);
            }
        }
    }

    // Swap a[i] and a[j].
    private static void swap(Vector v, int i, int j) {
        Object t = v.elementAt(i);
        v.setElementAt(v.elementAt(j), i);
        v.setElementAt(t, j);
    }
}

interface Comparator {
    // Returns -1 if o1 < o2, 0 if o1 == o2, 1 if o1 > o2.
    int compare(Object o1, Object o2);
}
