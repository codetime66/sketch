class DebugClassLoader extends ClassLoader {
    public synchronized Class loadClass(String name, boolean resolve)
        throws ClassNotFoundException {
            System.out.println("Loading " + name);
            return (findSystemClass(name));
        }
}
class Main {
    public static void main(String[] args) {
        System.out.println("ClassNotFound Example");
        if (args.length != 1) {
            System.err.println("usage: java Main <classname>");
            System.exit(1);
        }
        DebugClassLoader loader = new DebugClassLoader();
        try {
            loader.loadClass(args[0], true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
