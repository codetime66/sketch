class WhatCase {
    public static void charInfo(char ch) {
        System.out.print("'" + ch + "': ");
        if (!Character.isDefined(ch)) {
            System.out.println("**");
            return;
        }
        // case
        if (Character.isLowerCase(ch))
            System.out.print('l');
        else if (Character.isUpperCase(ch))
            System.out.print('u');
        else if (Character.isTitleCase(ch))
            System.out.print('t');
        else
            System.out.print('-');

        // letter or digit or space
        if (Character.isLetter(ch))
            System.out.println('l');
        else if (Character.isDigit(ch))
            System.out.println('d');
        else if (Character.isWhitespace(ch))
            System.out.println('s');
        else System.out.print('-');
        System.out.println();
    }

    public static void main(String[] args) {
        char[] buf = {'a', 'b', 'T', '5', '\t'};
        for (int i = 0; i < buf.length; i++)
            charInfo(buf[i]);
    }
}
