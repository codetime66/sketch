class Main {
    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println("This is a test");
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }
}
