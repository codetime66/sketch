class Main {
    public static void main(String[] args) {
        int inum = -279436;
        System.out.println(Integer.toString(inum, 10));
        System.out.println(Integer.toString(inum, 2));
        System.out.println(Integer.toString(inum, 8));
        System.out.println(Integer.toString(inum, 16));

        System.out.println(Integer.toString(inum));
        System.out.println(Integer.toBinaryString(inum));
        System.out.println(Integer.toOctalString(inum));
        System.out.println(Integer.toHexString(inum));
    }
}
