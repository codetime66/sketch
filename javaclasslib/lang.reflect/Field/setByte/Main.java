import java.lang.reflect.*;

class Main {
    public static void main(String[] args) {
        C c = new C();
        D d = new D();
        try {
            Field cf = C.class.getField("x");
            Field df = D.class.getField("x");

            System.out.println(cf.equals(df));          // true
            
            cf.setByte(d, (byte)8);
            cf.set(d, new Byte((byte)9));
            df.setByte(c, (byte)10);

            System.out.println(c.x);                    // 10.0
            System.out.println(d.x);                    // 9.0
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class C {
    public double x;
}
class D extends C {
}
