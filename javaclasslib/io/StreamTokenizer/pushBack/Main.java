import java.io.*;

class Node {
	public static final int TT_OPERATOR = 1;

	Node(Node type, Node left, Node right) {
	}
	Node(int type, Node left, Node right) {
	}
	Node(int type, double val) {
	}
	Node(int type, String str) {
	}
	Node(int type, char ch) {
	}
}
public class Main {
    public static Node parseExpr(StreamTokenizer tokens) throws IOException {
        Node left, op, right;
        int c = tokens.nextToken();
        switch (c) {
        case '(':
               left = parseToken(tokens);
               op = parseToken(tokens);
               right = parseToken(tokens);
               if (tokens.nextToken() != (int)')')  {
                   System.err.print("Unbalanced parenthesis");
                   return (null);
               }
               return new Node(op, left, right);
        default:
               tokens.pushBack();
               return (parseToken(tokens));
        }
    }
    public static Node parseToken(StreamTokenizer tokens) 
        throws IOException {
            switch (tokens.nextToken()) {
            case StreamTokenizer.TT_EOF:
                return (null);
            case StreamTokenizer.TT_NUMBER:
                return (new Node(tokens.ttype, tokens.nval));
            case StreamTokenizer.TT_WORD:
                return (new Node(tokens.ttype, tokens.sval));
            case '+':
            case '-':
            case '*':
            case '/':
                return (new Node(Node.TT_OPERATOR, (char)tokens.ttype));
            case '(':
                tokens.pushBack();
                return (parseExpr(tokens));
            }
            return (null);
    }
}
