class Main {
    public static void main(String[] args) {
        System.out.println("NegativeArraySizeException example");
        int[] intArray = new int[-5];
        intArray[3] = 10;
    }
}
