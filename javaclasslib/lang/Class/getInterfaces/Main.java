class Main {
    static void printInterfaces(Class c) {
        for (int i=0; i<c.getInterfaces().length; i++) {
            System.out.print(c.getInterfaces()[i].getName()+" ");
        }
	System.out.println();
    }
    public static void main(String[] args) {
        printInterfaces(C.class);    // I
        printInterfaces(D.class);    // 
        printInterfaces(E.class);    // I J
        printInterfaces(I.class);    // J
        printInterfaces(J.class);    //
    }
}

class C implements I {}

class D extends C {}

class E implements I, J {}

interface I extends J {}

interface J {}
