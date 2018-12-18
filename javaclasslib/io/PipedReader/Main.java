import java.io.*;
import java.util.Random;

// reads characters from pipe and counts vowels
class VowelCounter extends Thread {
    private Reader in;
    int vowels = 0;

    public VowelCounter(Reader in) {
        this.in = in;
    }
    static boolean isVowel(char c) {
        switch (c) {
        case 'a': 
        case 'e': 
        case 'i': 
        case 'o': 
        case 'u': 
            return true;
        }
        return false;
    }
    public void run() {
        int ch;
        try {
            while ((ch = in.read()) >= 0) {
                if (isVowel((char)ch)) {
                    vowels++;
                }
                // Prints letter and how many vowels counted so far
                System.out.print((char)ch + " " + vowels + " ");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class CharGenerator extends Thread {
    private Writer out;
    private Random gen = new Random();

    public CharGenerator(Writer out) {
        this.out = out;
    }
    public void run() {
        while (true) {
            try {
                // generate a character between 'a' and 'z'
                char ch = (char)('a' + Math.abs(gen.nextInt())%26);
                out.write(ch);
                out.flush();
                sleep(1000);  // sleep for 1 sec
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Main {
    public static void main(String[] args) {
        try {
            PipedWriter producer = new PipedWriter();
            PipedReader consumer = new PipedReader(producer);

            VowelCounter v = new VowelCounter(consumer);
            CharGenerator g = new CharGenerator(producer);

            v.start();
            g.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
