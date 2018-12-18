class Main {
    public static void main(String[] args) {
        short sval = 12;
        Short sobj = new Short((short)34); // cast to short needed 

        System.out.println(Short.toString(sval));
        System.out.println(sobj.toString());
    }
}
