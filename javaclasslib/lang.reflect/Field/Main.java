import java.lang.reflect.*;

class Main {
    // Print the names and values of all public fields in o.
    public static void printFields(Object o) {
        Field[] fields = o.getClass().getFields();

        try {
            for (int i=0; i<fields.length; i++) {
                System.out.println(fields[i].getName() + " = " + 
                                   fields[i].get(o));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // Increment all public fields by 1.
    public static void incFields(Object o) {
        Field[] fields = o.getClass().getFields();

        try {
            for (int i=0; i<fields.length; i++) {
                if (fields[i].getType() == byte.class) {
                    fields[i].setByte(o, (byte)(fields[i].getByte(o) + 1));
                } else if (fields[i].getType() == short.class) {
                    fields[i].setShort(o, (short)(fields[i].getShort(o) + 1));
                } else if (fields[i].getType() == char.class) {
                    fields[i].setChar(o, (char)(fields[i].getChar(o) + 1));
                } else if (fields[i].getType() == int.class) {
                    fields[i].setInt(o, fields[i].getInt(o) + 1);
                } else if (fields[i].getType() == long.class) {
                    fields[i].setLong(o, (long)(fields[i].getLong(o) + 1));
                } else if (fields[i].getType() == float.class) {
                    fields[i].setFloat(o, (float)(fields[i].getFloat(o) + 1));
                } else if (fields[i].getType() == double.class) {
                    fields[i].setDouble(o, 
                                        (double)(fields[i].getDouble(o) + 1));
                } else if (fields[i].getType() == String.class) {
                    fields[i].set(o, fields[i].get(o) + "1");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        C c = new C();

        printFields(c);
        incFields(c);
        printFields(c);
    }
}

class C {
    public byte b = 1;
    public short s = 2;
    public char c = 'a';
    public int i = 3;
    public long j = 4;
    public float f = 5.0f;
    public double d = 6.0;

    // Field can be static.
    static public String str = "Java";
}
