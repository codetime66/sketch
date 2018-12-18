import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        try {
            C c = new C();
            Field fi = C.class.getField("i");
            Field ff = C.class.getField("f");
            Field fs = C.class.getField("s");

            fi.set(c, new Integer(10));
            ff.set(c, new Integer(10));
            fs.set(c, "Java");
            // fi.set(c, null); NullPointerException
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

class C {
    public int i;
    public float f;
    public String s;
}
