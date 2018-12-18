import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;

class Main extends Frame {

    // Create text area and four break iterators
    TextArea tArea = 
        new TextArea("", 15, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
    TextArea message = 
        new TextArea("", 15, 50, TextArea.SCROLLBARS_NONE);

    BreakIterator charBI = BreakIterator.getCharacterInstance();
    BreakIterator wordBI = BreakIterator.getWordInstance();
    BreakIterator sentBI = BreakIterator.getSentenceInstance();
    BreakIterator lineBI = BreakIterator.getLineInstance();

    Main(String s, String sNative) {
        super("BreakIterator Example");

        // Initialize various objects with the text.
        tArea.setText(s);
        charBI.setText(sNative);
        wordBI.setText(sNative);
        sentBI.setText(sNative);
        lineBI.setText(sNative);

        message.setText(
          "Press the following letters to move the cursor.\n" +
          "Or click with the mouse to move to a new position.\n" +
          "\n" + 
          "  c = forward one character\n" +
          "  w = forward one word\n" +
          "  l = forward one potential line-break\n" +
          "  s = forward one sentence\n" +
          "\n" +
          "  Shift-c = backward one character\n" +
          "  Shift-w = backward one word\n" +
          "  Shift-l = backward one potential line-break\n" +
          "  Shift-s = backward one sentence\n" 
        );

        message.setEditable(false);

        // Add layout to text area and listen for key events.
        add(tArea, BorderLayout.CENTER);
        add(message, BorderLayout.SOUTH);
        tArea.addKeyListener(new KeyEventHandler());

        setSize(400, 400);
        pack();
        show();
    }

    class KeyEventHandler extends KeyAdapter {
        public void keyPressed(KeyEvent evt) {
            BreakIterator bi = null;
            int sel = tArea.getSelectionStart();

            switch (Character.toLowerCase(evt.getKeyChar())) {
              case 'c':    
                bi = charBI;
                break;
              case 'w':   
                bi = wordBI;
                break;
              case 'l':  
                bi = lineBI;
                break;
              case 's': 
                bi = sentBI;
                break;
            }

            if (bi != null) {
                // Get the length of text
                int end = bi.getText().getEndIndex();
                System.out.println(sel);
                if (sel < end) {
                   // Move using absolute position 
                   bi.following(sel);
                }
                if (sel <= end) {
                    // Are we moving to a previous boundary?
                    if (Character.isUpperCase(evt.getKeyChar())) {
                        bi.previous();
                        if (bi.current() >= sel) {
                            bi.previous();
                        }
                    }
                }
                tArea.select(bi.current(), bi.current());
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
