class Main {
    static double sqrt(double i) throws IllegalArgumentException {
        if (i < 0) 
            throw new IllegalArgumentException(
                "Cannot take square root of a negative number");

        return (Math.sqrt(i));
    }
    public static void main(String[] args) {
        System.out.println("IllegalArgumentException example");

        System.out.println(sqrt(-4));
    }
}
