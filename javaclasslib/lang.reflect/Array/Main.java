import java.lang.reflect.*;

class Main {
    Main() {
        double[] ds = new double[12];

        // Fill with random numbers.
        for (int i=0; i<ds.length; i++) {
            ds[i] = Math.random();
        }

        // Sort it.
        QuickSort.sort(ds, new Compare());

        // Print sorted list.
        for (int i=0; i<ds.length; i++) {
            System.out.println(ds[i]);
        }
    }

    class Compare implements Comparator {
        public int compare(Object a, Object b) {
            double i = ((Double)a).doubleValue();
            double j = ((Double)b).doubleValue();
            if (i < j) {
                return -1;
            } else if (i > j) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
