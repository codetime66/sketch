import java.lang.reflect.*;

class test {
    public static void main(String[] args) {
        try {
            Field f = D.class.getField("x");
            C c = new C();
            D d = new D();
            System.out.println(
                f.getDeclaringClass().getField("x").equals(c.getClass().getField("x")));
            System.out.println(f.getDeclaringClass());
            System.out.println(f.getInt(d));
            f.setByte(d, (byte)10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class C {
    public short x;
}
class D extends C {
}