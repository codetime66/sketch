class Main {
    public static void main(String[] args) {
        byte bval = 12;
        Byte bobj = new Byte((byte)34); // cast to byte needed 

        System.out.println(Byte.toString(bval));
        System.out.println(bobj.toString());
    }
}
