import java.util.Stack;class Main {    public static void main(String[] args) {        System.out.println("EmptyStackException example");        Stack s = new Stack();        System.out.println("push: " + s.push(new Integer(1)));        System.out.println("push: " + s.push(new Integer(2)));        System.out.println("push: " + s.push(new Integer(3)));        System.out.println("pop: " + s.pop());        System.out.println("pop: " + s.pop());        System.out.println("pop: " + s.pop());        System.out.println("pop: " + s.pop()); // empty stack    }}