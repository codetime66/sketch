class CMain {
    public static String makeTitle(String name) {
        char[] buf = new char[name.length()];
        boolean title = true;
        name.getChars(0, name.length(), buf, 0);

        // Capitalize first letter of each word

        for (int i = 0; i < buf.length; i++) {
            if (title)
                buf[i] = Character.toTitleCase(buf[i]);
            else
                buf[i] = Character.toLowerCase(buf[i]);
            title = Character.isWhiteSpace(buf[i]);
        }
        return (new String(buf));
    }

    public static void main(String[] args) {
        if (args.length == 1)
            System.out.println(makeTitle(args[0]));
        else
            System.out.println(makeTitle("this is a tEst"));
    }
}
