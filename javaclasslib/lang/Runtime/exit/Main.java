class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: java Main <arg>");
            Runtime.getRuntime().exit(-1);
        } 
        System.out.println(args[0]);
    }
}
