class Main {
    public static void main(String[] args) {
        long lnum = -1234567890123l;
        System.out.println(Long.toString(lnum, 10));
        System.out.println(Long.toString(lnum, 2));
        System.out.println(Long.toString(lnum, 8));
        System.out.println(Long.toString(lnum, 16));

        System.out.println(Long.toString(lnum));
        System.out.println(Long.toBinaryString(lnum));
        System.out.println(Long.toOctalString(lnum));
        System.out.println(Long.toHexString(lnum));
    }
}
