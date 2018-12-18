import java.lang.reflect.*;

class QuickSort {
    // Main sort routine.
    // If a is an array of primitive types, cmp.compare will be called
    // with the appropriate wrapper.
    public static void sort(Object a, Comparator cmp) {
        sort(a, 0, Array.getLength(a) - 1, cmp);
    }

    // Private recursive routine.
    private static void sort(Object a, int lo0, int hi0, Comparator cmp) {
        int lo = lo0;
        int hi = hi0;
        int mid;

        if (hi0 > lo0) {
            mid = (lo0 + hi0 ) / 2;

            // Loop through the array until indices cross.
            while(lo <= hi) {
	        while( (lo < hi0) && cmp.compare(Array.get(a, lo), Array.get(a, mid)) < 0) {
		    ++lo;
                }

	        while(hi > lo0 && cmp.compare(Array.get(a, hi), Array.get(a, mid)) > 0) {
		    --hi;
                }

                // if the indexes have not crossed, swap
                if(lo <= hi) {
                    swap(a, lo, hi);
                    ++lo;
                    --hi;
                }
            }

            if(lo0 < hi) {
                sort(a, lo0, hi, cmp);
            }

            if(lo < hi0) {
                sort(a, lo, hi0, cmp);
            }
        }
    }

    // Swap a[i] and a[j].
    private static void swap(Object a, int i, int j) {
        Object t = Array.get(a, i);
        Array.set(a, i, Array.get(a, j));
        Array.set(a, j, t);
    }
}
