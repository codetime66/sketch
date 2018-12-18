import java.lang.reflect.*;

class Main {
    // Returns an array with the same contents but double in size.
    public static Object expand(Object array) {
        Object result = Array.newInstance(array.getClass().getComponentType(), 
            Array.getLength(array)*2);

        // Copy the old contents to the new array.
        for (int i=0; i<Array.getLength(array); i++) {
            Array.set(result, i, Array.get(array, i));
        }

        // A faster alternative would be
        //System.arraycopy(array, 0, result, 0, Array.getLength(array));

        return result;
    }

    public static void main(String[] args) {
        int[] ints = {5, 4, 6, 9, 1};
        char[] chars = {'j', 'a', 'v', 'a'};

        ints = (int[])expand(ints);
        chars = (char[])expand(chars);
    }
}