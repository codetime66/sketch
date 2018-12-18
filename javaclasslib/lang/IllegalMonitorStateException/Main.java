class Main {
    public static void main(String[] args) {
        Integer a = new Integer(10);
        try {
            a.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
