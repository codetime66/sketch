import java.lang.reflect.*;

class Main {
    Main() {
        Method[] methods = getClass().getDeclaredMethods();

        for (int i=0; i<methods.length; i++) {
            System.out.println(methods[i].getName() + ": "
                + methods[i].getReturnType().getName());
        }
    }

    void m1() {
    }
    int m2() {
        return 0;
    }
    Integer m3() {
        return new Integer(0);
    }
    String m4() {
        return "0";
    }

    public static void main(String[] args) {
        new Main();
    }
}
