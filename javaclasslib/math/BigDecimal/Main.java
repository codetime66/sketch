import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.util.*;

class Main extends Frame implements ActionListener {
    // Used to show the current values.
    TextField display = new TextField();

    // Scale used internally for calculations.
    int inScale = 50;

    // Scale used to display answers.
    int outScale = 3;

    Main() {
        super("BigDecimal Example");
        Panel p = new Panel(new GridLayout(0, 4));
        String[] keys = {"ABS", "NEG", ">P", "<P", 
                         "7", "8", "9", "/", 
                         "4", "5", "6", "*",
                         "1", "2", "3", "-", 
                         "MIN", "0", ".", "+",
                         "MAX", "CE", "POP", "ENT" };

        // Create the panel.
        for (int i=0; i<keys.length; i++) {
            Button b = new Button(keys[i]);
            b.addActionListener(this);
            p.add(b);
        }
        add(p, BorderLayout.CENTER);

        // Add the display and show the frame.
        add(display, BorderLayout.NORTH);
        display.setEditable(false);
        pack();
        show();
    }

    // Stack of values.
    Stack stack = new Stack();

    // If the stack is empty return 0.
    BigDecimal pop() {
        if (stack.empty()) {
            return BigDecimal.valueOf(0, inScale);
        } 
        return (BigDecimal)stack.pop();
    }

    BigDecimal getDisplayValue() {
        if (display.getText() == null 
                || display.getText().length() == 0
                || display.getText().equals(".")) {
            return BigDecimal.valueOf(0, inScale);
        }
        BigDecimal n = new BigDecimal(display.getText());
        return n.setScale(Math.max(inScale, n.scale()));
    }

    // If true, all digits, periods will be appended to the value 
    // on the display.
    boolean inputMode;

    public void actionPerformed(ActionEvent evt) {
        BigDecimal n = getDisplayValue();
        String key = ((Button)evt.getSource()).getLabel();
        char c = key.charAt(0);
        boolean newInputMode = false;

        if ((c >= '0' && c <= '9') || c == '.') {
            if (!inputMode) {
                stack.push(n);
                inputMode = true;
            } else {
                key = display.getText() + key;
            }
            display.setText(key);
            return;
        } 

        if (key.equals("+")) {
            n = pop().add(n);
        } else if (key.equals("-")) {
            n = pop().subtract(n);
        } else if (key.equals("*")) {
            n = pop().multiply(n).setScale(inScale, BigDecimal.ROUND_HALF_UP);
        } else if (key.equals("/")) {
            n = pop().divide(n, BigDecimal.ROUND_HALF_UP);
        } else if (key.equals("ABS")) {
            n = n.abs();
            newInputMode = inputMode;
        } else if (key.equals("NEG")) {
            n = n.negate();
            newInputMode = inputMode;
        } else if (key.equals("<P")) {
            outScale = Math.max(0, outScale-1);
            newInputMode = inputMode;
        } else if (key.equals(">P")) {
            outScale++;
            newInputMode = inputMode;
        } else if (key.equals("MIN")) {
            n = pop().min(n);
        } else if (key.equals("MAX")) {
            n = pop().max(n);
        } else if (key.equals("CE")) {
            n = new BigDecimal(0).setScale(inScale);
        } else if (key.equals("POP")) {
            n = pop();
        } else if (key.equals("STO")) {
            stack.push(n);
        }

        inputMode = newInputMode;

        if (!inputMode) {
            // Display the number using the display scale.
            display.setText("" + 
                n.setScale(outScale, BigDecimal.ROUND_HALF_UP));
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
