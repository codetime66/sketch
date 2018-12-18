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

        // The set of array types on which getShort() can operate.
        Array.getShort(bytes, 0);
        Array.getShort(shorts, 0);

        // The set of array types on which setShort() can operate.
        Array.setShort(shorts, 0, (short)1);
        Array.setShort(ints, 0, (short)1);
        Array.setShort(longs, 0, (short)1);
        Array.setShort(floats, 0, (short)1);
        Array.setShort(doubles, 0, (short)1);
    }
}
