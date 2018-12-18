class Main {
    public static void main(String[] args) {
System.out.println(Character.getNumericValue('1'));      // 1
System.out.println(Character.getNumericValue('2'));      // 2
System.out.println(Character.getNumericValue('3'));      // 3
System.out.println(Character.getNumericValue('4'));      // 4
System.out.println(Character.getNumericValue('5'));      // 5
System.out.println(Character.getNumericValue('a'));      // 10
System.out.println(Character.getNumericValue('i'));      // 18
System.out.println(Character.getNumericValue('\u216c')); // 50
System.out.println(Character.getNumericValue('\u221e')); // -1 (infinity)
System.out.println(Character.getNumericValue(' '));      // -1
System.out.println(Character.getNumericValue(','));      // -1
System.out.println(Character.getNumericValue('\u2155')); // -2 (1/5)
System.out.println(Character.getNumericValue('\u215f')); // 1
System.out.println(Character.getNumericValue('\u00be')); // -2 (3/4)
    }
}
