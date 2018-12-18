class Main {
    public static void main(String[] args) {
        System.out.println("ArrayIndexOutOfBoundsException example");

        char[] buf = {'a', 'b', 'c'};
        int i;

        for (i = 0; i < buf.length; i++)
            System.out.println(buf[i]);
        
        System.out.println(buf[i]); // index out of bounds
    }
}
