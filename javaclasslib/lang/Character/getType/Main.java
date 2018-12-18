class Main {
    public static void main(String[] args) {
System.out.println(Character.getType('a'));        // LOWERCASE_LETTER
System.out.println(Character.getType('5'));        // DECIMAL_DIGIT_NUMBER
System.out.println(Character.getType('A'));        // UPPERCASE_LETTER
System.out.println(Character.getType('\n'));       // CONTROL
System.out.println(Character.getType(' '));        // SPACE_SEPARATOR
System.out.println(Character.getType('\u2029'));   // PARAGRAPH_SEPARATOR
System.out.println(Character.getType(';'));        // OTHER_PUNCTUATION
System.out.println(Character.getType('_'));        // CONNECTOR_PUNCTUATION
System.out.println(Character.getType('i'));        // LOWERCASE_LETTER
System.out.println(Character.getType('$'));        // CURRENCY_SYMBOL
System.out.println(Character.getType('<'));        // MATH_SYMBOL
    }
}
