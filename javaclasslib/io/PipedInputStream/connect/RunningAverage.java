import java.io.*;
import java.util.Random;

// reads numbers from input stream and compute running average
class RunningAverage extends Thread {
    private DataInputStream in;
    double total = 0;
    long count = 0;

    public RunningAverage(InputStream i) {
        in = new DataInputStream(i);
    }
    public void run() {
        while (true) {
            try {
                double num = in.readDouble();
                total += num;
                count++;
                System.out.println(count + ": " + num + " avg = " 
                                   + total/count);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class NumberGenerator extends Thread {
    private DataOutputStream out;
    private Random gen = new Random();
    private final long RANGE = 10000;

    public NumberGenerator(OutputStream o) {
        out = new DataOutputStream(o);
    }
    public void run() {
        while (true) {
            try {
                double num = gen.nextFloat() * RANGE;
                out.writeDouble(num);
                sleep(500);  // sleep for 500 milliseconds
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
