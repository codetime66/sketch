import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            PipedOutputStream producer = new PipedOutputStream();
            PipedInputStream consumer = new PipedInputStream();

            consumer.connect(producer);        // connect pipes

            RunningAverage avg = new RunningAverage(consumer);
            NumberGenerator gen = new NumberGenerator(producer);

            gen.start();             // start threads
            avg.start();

            try {
                Thread.sleep(5000);  // sleep for 5 seconds
            } catch (InterruptedException e) {
            }

            gen.stop();              // stop threads
            avg.stop();

            producer.close();        // close streams
            consumer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
