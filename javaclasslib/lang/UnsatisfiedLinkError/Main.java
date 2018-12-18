class Main {
    public static void main(String[] args) {
        System.out.println("UnsatisfiedLinkError example");

        System.load("NeverFindThisOne"); // link error
    }
}
