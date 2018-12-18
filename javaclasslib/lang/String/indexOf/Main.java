class Main {
    public static void main(String[] args) {
        String s = "Madam, I'm Adam";

        System.out.println(s.indexOf('\''));        // 8
        System.out.println(s.indexOf('\'', 8));     // 8
        System.out.println(s.indexOf('\'', 9));     // -1
        System.out.println(s.indexOf('a', 100));    // -1
        System.out.println(s.indexOf('X'));         // -1

        // Retrieve the first word.
        System.out.println(
            s.substring(0, s.indexOf(' ')));        // Madam,
    }
}
