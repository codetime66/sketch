import java.lang.reflect.*;

class Main {
    // Prints all the public methods except for ones from Object.
    static void printMethods(Method[] methods) {
        for (int i=0; i<methods.length; i++) {
            if (methods[i].getDeclaringClass() != Object.class) {
                System.out.println(methods[i]);
            }
        }
    }

    public static void main(String[] args) {
        printMethods(C.class.getMethods());
                                                // public void C.c_pub1()
                                                // public void C.c_pub2()
        printMethods(D.class.getMethods());
                                                // public void D.c_pub1()
                                                // public void C.c_pub2()
                                                // public void D.d_pub()
        printMethods(I.class.getMethods());
                                                // public abstract void I.i_m()
        printMethods(J.class.getMethods());
                                                // public abstract void I.i_m()
                                                // public abstract void J.j_m()
    }
}

class C {
    private void c_prv() {}
    public void c_pub1() {}
    public void c_pub2() {}
    protected void c_prot() {}
}

class D extends C {
    private void d_prv() {}
    public void c_pub1() {}
    public void d_pub() {}
}

interface I {
    void i_m();
}

interface J extends I {
    void j_m();
}
