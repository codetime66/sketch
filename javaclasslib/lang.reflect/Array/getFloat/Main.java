import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        byte[] bytes = new byte[1];
        char[] chars = new char[1];
        short[] shorts = new short[1];
        int[] ints = new int[1];
        long[] longs = new long[1];
        float[] floats = new float[1];
        double[] doubles = new double[1];

        // The set of array types on which getFloat() can operate.
        Array.getFloat(bytes, 0);
        Array.getFloat(shorts, 0);
        Array.getFloat(chars, 0);
        Array.getFloat(ints, 0);
        Array.getFloat(longs, 0);
        Array.getFloat(floats, 0);

        // The set of array types on which setFloat() can operate.
        Array.setFloat(floats, 0, 1L);
        Array.setFloat(doubles, 0, 1L);
    }
}
