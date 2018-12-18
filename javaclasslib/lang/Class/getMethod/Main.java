import java.lang.reflect.*;

class Main {
    static void print(Method m) {
	System.out.println(m);
    }

    public static void main(String[] args) {
        try {
            // print(C.class.getMethod("c_prv", new Class[]{}));  
                                           // NoSuchMethodException
            print(C.class.getMethod("c_pub1", new Class[]{}));    
                                           // public void C.c_pub1()
            print(C.class.getMethod("c_pub1", new Class[]{}));    
                                           // public void C.c_pub1()

            print(D.class.getMethod("c_pub1", new Class[]{}));    
                                           // public void D.c_pub1()
            print(D.class.getMethod("c_pub2", new Class[]{}));    
                                           // public void C.c_pub2()
            print(D.class.getMethod("d_pub", new Class[]{}));     
                                           // public void D.d_pub()

            print(I.class.getMethod("i_m", new Class[]{}));    
                                           // public abstract void I.i_m()
            print(J.class.getMethod("i_m", new Class[]{}));    
                                           // public abstract void I.i_m()
            print(J.class.getMethod("j_m", new Class[]{}));    
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
