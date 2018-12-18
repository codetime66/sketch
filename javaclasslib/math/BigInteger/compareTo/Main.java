import java.math.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

class Main {
    static class Compare implements Comparator {
        public int compare(Object a, Object b) {
            return ((BigInteger)a).compareTo((BigInteger)b);
        }
    }

    public static void main(String[] args) {
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Vector v = new Vector();

        // Save the string numbers in v.
        try {
            while ((line = is.readLine()) != null) {
                v.addElement(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Copy v into an array of big nums.
        BigInteger[] nums = new BigInteger[v.size()];
        for (int i=0; i<nums.length; i++) {
            nums[i] = new BigInteger((String)v.elementAt(i));
        }

        // Sort the list.
        QuickSort.sort(nums, new Compare());

        // Print sorted list.
        for (int i=0; i<nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
