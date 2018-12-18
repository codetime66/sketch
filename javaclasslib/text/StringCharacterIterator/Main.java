import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.StringCharacterIterator;

class Main extends Frame {
    TextArea textArea = 
        new TextArea("", 20, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
    TextArea message = 
        new TextArea("", 10, 50, TextArea.SCROLLBARS_NONE);

    StringCharacterIterator sci = null;
    StringCharacterIterator sci1 = null;
    StringCharacterIterator sci2 = null;
    boolean subrangeflag = false;

    Main(String s, String sNative) {
        super("StringCharacterIterator Example");

        // Set the text and create the iterator.
        textArea.setText(s);
        sci1 = new StringCharacterIterator(sNative);

        // Create the subrange iterator.
        int end = (int)Math.round (0.7 * sNative.length());
        int begin  = (int)Math.round (0.3 * sNative.length());

        // Diagnostics:
        System.out.println("start of subrange: " + begin);
        System.out.println("end of subrange:   " + end);

        sci2 = new StringCharacterIterator(sNative, begin, end, begin);

        // Set the initial iterator.
        sci = sci1;

        // Display the instructions
        message.setText(
          "Press the following letters to move the cursor.\n" +
          "Or click with the mouse to move to a new position.\n" +
          "\n" + 
          "  n = to move to next character\n" +
          "  p = to move to previous character\n" +
          "  f = to move to first character\n" +
          "  l = to move to last character\n" +
          "  t = to toggle between full range and subrange\n" 
        );

        message.setEditable(false);

        // Create text area and listen for key events.
        add(textArea, BorderLayout.CENTER);
        add(message, BorderLayout.SOUTH);
        textArea.addKeyListener(new KeyEventHandler());

        setSize(400, 400);
        show();
        textArea.select(sci.getBeginIndex(), sci.getEndIndex() - 1);
        textArea.setCaretPosition(sci.getIndex());
    }

    class KeyEventHandler extends KeyAdapter {
        public void keyPressed(KeyEvent evt) { 

            // In case the user clicks, get the caret position.
            int caret = textArea.getCaretPosition();

            // Set the iterator to match the caret.
            if (subrangeflag) {
                if (caret < (sci.getEndIndex() - 1)) {
                    sci.setIndex(caret);
                }
            } else {
                if (caret < textArea.getText().length()) {
                    sci.setIndex(caret);
                }
            }
            char rtn = '_';

            // Branch according to key pressed.
            switch (Character.toLowerCase(evt.getKeyChar())) {
              case 'n':    
                  //  Workaround for bug in next().
                  if (sci.getIndex() < sci.getEndIndex()) {
                      rtn = sci.next();
                  }
                  break;
              case 'p':   
                  rtn = sci.previous();
                  break;
              case 'f':  
                  rtn = sci.first();
                  break;
              case 'l': 
                  rtn = sci.last();
                  break;
              case 't':    
                  // Toggle between range and subrange.

                  if (subrangeflag) {
                      sci = sci1;
                      subrangeflag = false;
                  } else {
                      sci = sci2;
                      subrangeflag = true;
                  }

                  // Diagnostics:
                  System.out.println("");
                  System.out.println("getBeginIndex:   " + sci.getBeginIndex());
                  System.out.println("getEndIndex:     " + sci.getEndIndex());

                  // Highlight the range.
                  textArea.select(sci.getBeginIndex(), sci.getEndIndex() - 1);
                  break;
            }

            // Set the caret position.
            textArea.setCaretPosition(sci.getIndex());

            // Diagnostics:
            System.out.println("");
            System.out.println("current:      " + sci.current());
            System.out.println("getIndex:     " + sci.getIndex());
            if (rtn == StringCharacterIterator.DONE) {
                System.out.println("character:    " + "DONE");
            } else {
                System.out.println("character:    " + rtn); 
            }

            evt.consume();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <filename>");
            System.exit(1);
        }
        try {
            // To workaround a TextArea bug, create two versions
            // of the text: 
            // - one with '\n' for newlines (to display in text area)
            // - the other with native newlines (to iterate over)
            // Read in the entire contents of the file.
            BufferedReader rd = new BufferedReader(new FileReader(args[0]));
            String sep = System.getProperty("line.separator");
            String line;
            StringBuffer sbuf = new StringBuffer();
            StringBuffer sbufNative = new StringBuffer();

            while ((line = rd.readLine()) != null) {
                sbuf.append(line);
                sbuf.append('\n');
                sbufNative.append(line);
                sbufNative.append(sep);
            }
            rd.close();
            new Main(new String(sbuf), new String(sbufNative));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
