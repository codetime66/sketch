import java.lang.reflect.*;

class Main {
    // Prints all the methods except for ones from Object.
    static void printMethods(Method[] methods) {
        for (int i=0; i<methods.length; i++) {
            if (methods[i].getDeclaringClass() != Object.class) {
                System.out.println(methods[i]);
            }
        }
    }

    public static void main(String[] args) {
        printMethods(C.class.getDeclaredMethods());
                                                // private void C.c_prv()
                                                // public void C.c_pub1()
                                                // public void C.c_pub2()
        printMethods(D.class.getDeclaredMethods());
                                                // private void D.d_prv()
                                                // public void D.c_pub1()
                                                // public void D.d_pub()
        printMethods(I.class.getDeclaredMethods());
                                                // public abstract void I.i_m()
        printMethods(J.class.getDeclaredMethods());
                                                // public abstract void J.j_m()
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
