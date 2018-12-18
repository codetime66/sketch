import java.lang.reflect.*;

class Main {
    // Prints all the methods except for ones from Object.
    static void print(Method m) {
        System.out.println(m);
    }

    public static void main(String[] args) {
        Class[] pt = new Class[]{};
        try {
            print(C.class.getDeclaredMethod("c_prv", pt));   
                                             // private void c_prv()
            print(C.class.getDeclaredMethod("c_pub1", pt));  
                                             // public void C.c_pub1()
            print(C.class.getDeclaredMethod("c_pub1", pt));  
                                             // public void C.c_pub1()

            print(D.class.getDeclaredMethod("c_pub1", pt));  
                                             // public void D.c_pub1()
            //print(D.class.getDeclaredMethod("c_pub2", pt));
                                             // NoSuchMethodException
            print(D.class.getDeclaredMethod("d_pub", pt));   
                                             // public void D.d_pub()

            print(I.class.getDeclaredMethod("i_m", pt));     
                                             // public abstract void I.i_m()
            //print(J.class.getDeclaredMethod("i_m", pt));   
                                             // NoSuchMethodException
            print(J.class.getDeclaredMethod("j_m", pt));     
                                             // public abstract void J.j_m()
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

class C {
    private void c_prv() {}
    public void c_pub1() {}
    public void c_pub2() {}
}

class D extends C {
    private void d_prv() {}
    public void c_pub1() {}    // override
    public void d_pub() {}
}

interface I {
    void i_m();
}

interface J extends I {
    void j_m();
}
