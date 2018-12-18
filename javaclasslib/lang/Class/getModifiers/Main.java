import java.lang.reflect.*;

class Main {
    static void printModifiers(int mods) {
        System.out.println(Modifier.toString(mods));
    }

    public static void main(String[] args) {
        printModifiers(String.class.getModifiers());
                                            // public final synchronized
        printModifiers(Cloneable.class.getModifiers());
                                            // public interface
        printModifiers(java.awt.Component.class.getModifiers());
                                            // public abstract synchronized

        // Inner classes
        printModifiers(Main.InnerA.class.getModifiers());
                                            // abstract
        printModifiers(Main.InnerB.class.getModifiers());
                                            // final
        printModifiers(Main.InnerC.class.getModifiers());
                                            // public
    }

    static abstract class InnerA {}
    static final private class InnerB {}
    static public class InnerC {}
}

