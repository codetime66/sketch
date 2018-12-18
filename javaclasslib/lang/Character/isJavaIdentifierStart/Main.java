class Main {
    public static boolean validJavaIdentifier(String str) {
        char[] buf = new char[str.length()];
        str.getChars(0, buf.length, buf, 0);

        if (!Character.isJavaIdentifierStart(buf[0]))
            return false;

        for (int i = 1; i < buf.length; i++) {
            if (!Character.isJavaIdentifierPart(buf[i]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++)
            System.out.println(args[i] + ":" + 
                (validJavaIdentifier(args[i]) ? "OK" : "Invalid"));
    }
}
