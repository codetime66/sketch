import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        boolean[] booleans = new boolean[1];
        byte[] bytes = new byte[1];
        char[] chars = new char[1];
        short[] shorts = new short[1];
        int[] ints = new int[1];
        long[] longs = new long[1];
        float[] floats = new float[1];
        double[] doubles = new double[1];
        Object[] objects = new Object[1];
        String[] strings = new String[1];

        // Different ways that set() can be used.
        Array.set(booleans, 0, Boolean.TRUE);
        Array.set(bytes, 0, new Byte((byte)1));
        Array.set(chars, 0, new Character('a'));
        Array.set(shorts, 0, new Short((short)1));
        Array.set(ints, 0, new Integer(1));
        Array.set(longs, 0, new Long(1L));
        Array.set(floats, 0, new Float(1.0f));
        Array.set(doubles, 0, new Double(1));
        Array.set(objects, 0, System.out);
        Array.set(strings, 0, "abc");

        // Different ways that get() can be used.
        Boolean bool = (Boolean)Array.get(booleans, 0);
        Byte b = (Byte)Array.get(bytes, 0);
        Character c = (Character)Array.get(chars, 0);
        Short s = (Short)Array.get(shorts, 0);
        Integer i = (Integer)Array.get(ints, 0);
        Long l = (Long)Array.get(longs, 0);
        Float f = (Float)Array.get(floats, 0);
        Double d = (Double)Array.get(doubles, 0);
        Object obj = (Object)Array.get(objects, 0);
        String str = (String)Array.get(strings, 0);

        // Demonstration of widening conversions.
        Array.set(ints, 0, new Byte((byte)1));
        Array.set(doubles, 0, new Integer(1));
    }
}
