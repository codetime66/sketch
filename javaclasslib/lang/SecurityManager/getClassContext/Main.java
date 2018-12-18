public class Main {
    public static void main (String[] args) {
        System.setSecurityManager(new MySecurityManager());

        C.printContext();
    }
}

class MySecurityManager extends SecurityManager {
    public void printContext() {
        Class[] c = getClassContext();

        for (int i=0; i<c.length; i++) {
            System.out.println(c[i]);
        }
    }
}

class C {
    static void printContext() {
        ((MySecurityManager)
            System.getSecurityManager()).printContext();
    }
}
