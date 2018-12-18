import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        try {
            C c = new C();
            Field fi = C.class.getField("i");
            Field ff = C.class.getField("f");
            Field fs = C.class.getField("s");

            System.out.println(fi.get(c).getClass());
            System.out.println(ff.get(c).getClass());
            System.out.println(fs.get(c).getClass());
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
    public String s = "Java";
}
