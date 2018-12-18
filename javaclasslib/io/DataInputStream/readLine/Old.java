import java.io.*;

class Old {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("java Old <inputfile>");
            System.exit(-1);
        }
        try {
            DataInputStream in = 
                new DataInputStream(new FileInputStream(args[0]));

            String line;
            while ((line=in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
