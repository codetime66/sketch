class Main {
    public static void main(String[] args) {
        String s = "Madam, I'm Adam";

        System.out.println(s.lastIndexOf('\''));        // 8
        System.out.println(s.lastIndexOf('\'', 8));     // 8
        System.out.println(s.lastIndexOf('\'', 7));     // -1
        System.out.println(s.lastIndexOf('a', -1));     // -1
        System.out.println(s.lastIndexOf('X'));         // -1

        // Retrieve the last word.
        System.out.println(
            s.substring(s.lastIndexOf(' ')+1));         // Adam
    }
}
